package com.movie.app.services.impls;

import com.movie.app.exceptions.TokenExpiredException;
import com.movie.app.services.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    private final String KEY_SECRET = "Cc6tJXABIioNn5fSRTfhTcPWky0lFnSP7eZL2pS9b4upvEE7oT7Qn11KHyVxXt0I\n";


    @Override
    public String extractUsername(String token) {
        return this.extractClaims(token, Claims::getSubject);
    }

    @Override
    public String generate(UserDetails userDetails) {
        return this.generate(userDetails, new HashMap<>());
    }

    @Override
    public String generate(UserDetails userDetails, Map<String, Object> claims) {
        long timeCurrent = System.currentTimeMillis();
        long EXPIRATION_TIME_MS = 3600 * 1000 * 2;
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(timeCurrent))
                .setExpiration(new Date(timeCurrent + EXPIRATION_TIME_MS))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            // Extract expiration claim from the token
            Date expirationDate = this.extractClaims(token, Claims::getExpiration);
            // Check if the token is expired
            if (expirationDate.before(new Date())) {
                throw new TokenExpiredException("Token has expired");
            }
            // If the token is not expired, return true
            return true;
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
        }
        // Return false for any other exceptions
        return false;
    }
}
