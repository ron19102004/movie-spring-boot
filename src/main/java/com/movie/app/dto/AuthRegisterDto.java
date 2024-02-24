package com.movie.app.dto;

import com.movie.app.entities.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRegisterDto {
    @NotNull
    @Length(min = 5)
    private String fullName;
    @NotNull
    @Length(min = 15)
    private String email;
    @NotNull
    @Length(min = 5)
    private String username;
    @NotNull
    @Length(min = 8)
    private String password;
    @NotNull
    @Length(min = 10, max = 11)
    private String phone;

    public Map<String,Object> response(User user){
        Map<String,Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("password",user.getPassword());
        data.put("fullName",user.getFullName());
        data.put("vip",user.getIs_vip());
        data.put("phone",user.getPhone());
        data.put("email",user.getEmail());
        return data;
    }
}
