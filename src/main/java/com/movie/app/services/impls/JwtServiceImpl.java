package com.movie.app.services.impls;

import com.movie.app.exceptions.TokenExpiredException;
import com.movie.app.services.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
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
    private final int expirate = 60 * 60 * 24;

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
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirate))
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
            boolean validExpired = this.extractClaims(token, Claims::getExpiration).before(new Date());
            if (validExpired) {
                throw new TokenExpiredException("Token has expired");
            }
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
        return false;
    }
}
