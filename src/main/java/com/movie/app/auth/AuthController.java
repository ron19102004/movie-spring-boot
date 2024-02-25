package com.movie.app.auth;

import com.movie.app.aws.AwsS3Service;
import com.movie.app.dto.AuthRegisterDto;
import com.movie.app.dto.AuthSignInDto;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/upload")
    public String up(@RequestParam(value = "file") @NotNull MultipartFile file){
        return this.awsS3Service.upload(file,"image");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.builder()
                .status(false)
                .message("Error!!")
                .data(exception.getMessage())
                .build());
    }
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
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseData> handleSQLException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ResponseData.builder()
                .status(false)
                .message("Server Error!!")
                .data("")
                .build());
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseData> handleServiceException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.builder()
                .status(false)
                .message("Error!!")
                .data(exception.getMessage())
                .build());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseData> handleAuthenticationException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseData.builder()
                .status(false)
                .message("Authentication Error!!")
                .data(exception.getMessage())
                .build());
    }
}
