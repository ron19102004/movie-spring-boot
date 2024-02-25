package com.movie.app.services;

import com.movie.app.dto.CreateCountryDto;
import com.movie.app.entities.Country;

import java.util.List;

public interface CountryService {
    Country create(CreateCountryDto createCountryDto);
    List<Country> findAll();
    void delete(Long id);
    Country update(Long id,String name);
}
