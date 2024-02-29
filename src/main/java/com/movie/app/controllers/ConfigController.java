package com.movie.app.controllers;

import com.movie.app.dto.CreateConfigDto;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.ConfigService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/config")
public class ConfigController extends GlobalExceptionHandler {
    @Autowired
    private ConfigService configService;

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> create(@RequestBody @NotNull CreateConfigDto createConfigDto) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Created!")
                .data(this.configService.create(createConfigDto))
                .build());
    }

    @PatchMapping("/update/icon/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> updateIcon(@PathVariable("id") @NotNull Long id,
                                                   @RequestParam("file") @NotNull MultipartFile file) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Updated!")
                .data(this.configService.updateIconWeb(id, file))
                .build());
    }

    @PatchMapping("/update/banner/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> updateBanner(@PathVariable("id") @NotNull Long id,
                                                     @RequestParam("file") @NotNull MultipartFile file) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Updated!")
                .data(this.configService.updateBannerIntro(id, file))
                .build());
    }

    @PatchMapping("/active/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> doActive(@PathVariable("id") @NotNull Long id) {
        this.configService.activeConfig(id);
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Active ok!")
                .data(null)
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> delete(@PathVariable("id") @NotNull Long id) {
        this.configService.delete(id);
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Deleted!")
                .data(null)
                .build());
    }
}
