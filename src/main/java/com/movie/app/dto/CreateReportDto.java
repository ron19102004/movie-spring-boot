package com.movie.app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateReportDto {
    @NotNull
    @NotBlank
    @Length(min = 12)
    private String email;
    @NotNull
    @NotBlank
    private String content;
}
