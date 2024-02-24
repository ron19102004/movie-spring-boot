package com.movie.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthSignInDto {
    private String username;
    private String password;

    public Map<String,String> response(String accessToken,String refreshToken){
        Map<String,String> data = new HashMap<>();
        data.put("access_token",accessToken);
        data.put("refresh_token",refreshToken);
        return data;
    }
}
