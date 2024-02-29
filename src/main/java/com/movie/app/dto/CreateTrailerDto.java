package com.movie.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTrailerDto {
    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    private MultipartFile file;
}
