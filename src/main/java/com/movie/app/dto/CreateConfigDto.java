package com.movie.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateConfigDto {
    @NotNull
    @NotBlank
    private String nameWeb;
    @Null
    private String emailAdmin;
    @Null
    private String facebookPageLink;
}
