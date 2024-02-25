package com.movie.app.exceptions;

import com.movie.app.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseData> handleSQLException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ResponseData.builder()
                .status(false)
                .message("Server Error!!")
                .data(null)
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

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseData> handleServiceException(ServiceException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseData.builder()
                        .status(false)
                        .message(e.getMessage())
                        .data(null)
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseData> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseData.builder()
                        .status(false)
                        .message(e.getMessage())
                        .data(null)
                        .build());
    }
}