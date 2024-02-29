package com.movie.app.controllers;

import com.movie.app.dto.CreateTrailerDto;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.TrailerService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/trailers")
public class TrailerController extends GlobalExceptionHandler {
    @Autowired
    private TrailerService trailerService;

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> create(
            @RequestParam("url") @NotNull @NotBlank String url,
            @RequestParam("name") @NotNull @NotBlank String name,
            @RequestParam("image") @NotNull MultipartFile file) {
        CreateTrailerDto createTrailerDto = CreateTrailerDto.builder()
                .url(url)
                .name(name)
                .file(file)
                .build();
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Created!")
                .data(this.trailerService.create(createTrailerDto))
                .build());
    }
}
