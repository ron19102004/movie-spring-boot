package com.movie.app.services;

import com.movie.app.dto.CreateTrailerDto;
import com.movie.app.entities.Trailer;
import com.movie.app.interfaces.ServiceReader;
import com.movie.app.interfaces.ServiceWriter;

public interface TrailerService extends
        ServiceReader<Trailer, Long>,
        ServiceWriter<Trailer, Long, CreateTrailerDto, Trailer> {
}
