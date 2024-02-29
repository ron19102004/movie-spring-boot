package com.movie.app.services;

import com.movie.app.dto.CreateReportDto;
import com.movie.app.entities.Report;
import com.movie.app.interfaces.ServiceCreate;
import com.movie.app.interfaces.ServiceReader;

public interface ReportService extends
        ServiceReader<Report, Long>,
        ServiceCreate<Report, CreateReportDto> {
}
