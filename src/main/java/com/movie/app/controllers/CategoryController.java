package com.movie.app.controllers;

import com.movie.app.entities.Category;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.CategoryService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends GlobalExceptionHandler {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> create(@RequestParam("name") @NotNull @Length(min = 2) String name,
                                               @RequestParam("file") @NotNull MultipartFile file) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Created!")
                .data(this.categoryService.create(name, file))
                .build());
    }
    @GetMapping("")
    public ResponseEntity<ResponseData> getAll() {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Got!")
                .data(this.categoryService.find())
                .build());
    }
    @PatchMapping("/update-image/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> updateImage(
            @PathVariable("id") @NotNull Long id,
            @RequestParam("file") @NotNull MultipartFile file) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Updated!")
                .data(this.categoryService.updateImage(id, file))
                .build());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> delete(
            @PathVariable("id") @NotNull Long id) {
        this.categoryService.delete(id);
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Deleted!")
                .data(null)
                .build());
    }
}
