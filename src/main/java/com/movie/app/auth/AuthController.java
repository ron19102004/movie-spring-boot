package com.movie.app.auth;

import com.movie.app.dto.AuthRegisterDto;
import com.movie.app.dto.AuthSignInDto;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends GlobalExceptionHandler {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(@RequestBody @NotNull AuthRegisterDto authRegisterDto) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .status(true)
                        .message("Register successfully!!")
                        .data(this.authService.register(authRegisterDto))
                        .build());
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody @NotNull AuthSignInDto authSignInDto){
        return ResponseEntity.ok(
                ResponseData.builder()
                        .status(true)
                        .message("Login successfully!!")
                        .data(this.authService.login(authSignInDto))
                        .build());
    }

}
