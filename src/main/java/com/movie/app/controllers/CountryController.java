package com.movie.app.controllers;

import com.movie.app.dto.CreateCountryDto;
import com.movie.app.dto.UpdateCountryDto;
import com.movie.app.exceptions.GlobalExceptionHandler;
import com.movie.app.services.CountryService;
import com.movie.app.utils.ResponseData;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/countries")
public class CountryController extends GlobalExceptionHandler {
    @Autowired
    private CountryService countryService;

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> create(@RequestBody @NotNull CreateCountryDto createCountryDto) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Created!")
                .data(this.countryService.create(createCountryDto))
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseData> getAll() {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Query successfully!")
                .data(this.countryService.findAll())
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> delete(@PathVariable(value = "id") @NotNull Long id) {
        this.countryService.delete(id);
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Deleted!")
                .data(null)
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> update(@PathVariable(value = "id") @NotNull Long id,
                                               @RequestBody @NotNull UpdateCountryDto updateCountryDto) {
        return ResponseEntity.ok(ResponseData.builder()
                .status(true)
                .message("Deleted!")
                .data(this.countryService.update(id, updateCountryDto.getName()))
                .build());
    }

}
