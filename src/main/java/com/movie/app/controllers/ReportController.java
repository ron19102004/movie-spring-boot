package com.movie.app.controllers;

import com.movie.app.dto.CreateReportDto;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.ReportService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController extends GlobalExceptionHandler {
    @Autowired
    private ReportService reportService;

    @PostMapping("/new")
    public ResponseEntity<ResponseData> create(@RequestBody @NotNull CreateReportDto createReportDto) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Created!")
                .data(this.reportService.create(createReportDto))
                .build());
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> getAll() {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Got!")
                .data(this.reportService.find())
                .build());
    }
}
