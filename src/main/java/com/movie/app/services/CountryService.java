package com.movie.app.services;

import com.movie.app.dto.CreateCountryDto;
import com.movie.app.entities.Country;
import com.movie.app.interfaces.ServiceReader;
import com.movie.app.interfaces.ServiceWriter;

public interface CountryService
        extends
        ServiceWriter<Country, Long, CreateCountryDto, String>,
        ServiceReader<Country, Long> {
}
