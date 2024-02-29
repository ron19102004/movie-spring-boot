package com.movie.app.services.impls;

import com.movie.app.dto.CreateReportDto;
import com.movie.app.entities.Report;
import com.movie.app.repositories.ReportRepository;
import com.movie.app.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Optional<Report> findById(Long id) {
        return this.reportRepository.findById(id);
    }

    @Override
    public List<Report> find() {
        return this.reportRepository.findAll();
    }

    @Override
    public Report create(CreateReportDto createReportDto) {
        Report report = Report.builder()
                .email(createReportDto.getEmail())
                .content(createReportDto.getContent())
                .build();
        return this.reportRepository.save(report);
    }

}
