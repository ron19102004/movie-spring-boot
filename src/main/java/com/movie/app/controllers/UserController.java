package com.movie.app.controllers;

import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.UserService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends GlobalExceptionHandler {
    @Autowired
    private UserService userService;

    @PatchMapping("/change-role/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> changeRole(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Changed!")
                .data(this.userService.changeRole(id))
                .build());
    }
}
