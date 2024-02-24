package com.movie.app.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    Claims extractAllClaims(String token);
    <T> T extractClaims(String token, Function<Claims,T> claimResolver);
    String generate(UserDetails userDetails);
    String generate(UserDetails userDetails, Map<String,Object> claims);
    boolean isTokenValid(String token);
}
