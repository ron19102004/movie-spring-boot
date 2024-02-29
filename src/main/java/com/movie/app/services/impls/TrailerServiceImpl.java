package com.movie.app.services.impls;

import com.movie.app.aws.AwsS3Service;
import com.movie.app.dto.CreateTrailerDto;
import com.movie.app.entities.Trailer;
import com.movie.app.repositories.TrailerRepository;
import com.movie.app.services.TrailerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrailerServiceImpl implements TrailerService {
    @Autowired
    private TrailerRepository trailerRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AwsS3Service awsS3Service;
    private final String folder = "trailer";

    @Override
    public Optional<Trailer> findById(Long id) {
        return this.trailerRepository.findById(id);
    }

    @Override
    public List<Trailer> find() {
        return this.trailerRepository.findAll();
    }

    @Override
    public Trailer create(CreateTrailerDto createTrailerDto) {
        String image = this.awsS3Service.upload(createTrailerDto.getFile(), this.folder);
        return this.trailerRepository.save(Trailer.builder()
                .image(image)
                .url(createTrailerDto.getUrl())
                .name(createTrailerDto.getName())
                .build());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Trailer update(Long aLong, Trailer trailer) {
        return null;
    }
}

