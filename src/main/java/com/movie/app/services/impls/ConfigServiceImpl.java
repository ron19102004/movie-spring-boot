package com.movie.app.services.impls;

import com.movie.app.aws.AwsS3Service;
import com.movie.app.dto.CreateConfigDto;
import com.movie.app.entities.Config;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.repositories.ConfigRepository;
import com.movie.app.services.ConfigService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private AwsS3Service awsS3Service;
    private final String folderName = "config";

    @Override
    public Optional<Config> findById(Long id) {
        return this.configRepository.findById(id);
    }

    @Override
    public Config create(CreateConfigDto createConfigDto) {
        Config config = Config.builder()
                .nameWeb(createConfigDto.getNameWeb())
                .emailAdmin(createConfigDto.getEmailAdmin())
                .facebookPageLink(createConfigDto.getFacebookPageLink())
                .isActive(false)
                .build();
        return this.configRepository.save(config);
    }

    @Override
    public void activeConfig(Long id) {
        Config configId = this.findById(id).orElseThrow(() -> new ServiceException("ConfigNotFound"));
        List<Config> all = this.configRepository.findAllByIsActive(true);
        if (all.size() == 1) {
            Config configDb = all.get(0);
            configDb.setIsActive(false);
            this.entityManager.merge(configDb);
        }
        configId.setIsActive(true);
        this.entityManager.merge(configId);
    }

    @Override
    public void delete(Long id) {
        this.configRepository.deleteById(id);
    }

    @Override
    public List<Config> find() {
        return this.configRepository.findAll();
    }

    @Override
    public Config updateIconWeb(Long id, MultipartFile file) {
        Config byId = this.findById(id).orElseThrow(() -> new ServiceException("Config not found"));
        String url = this.awsS3Service.upload(file, this.folderName);
        byId.setIconWeb(url);
        return this.entityManager.merge(byId);
    }

    @Override
    public Config updateBannerIntro(Long id, MultipartFile file) {
        Config byId = this.findById(id).orElseThrow(() -> new ServiceException("Config not found"));
        String url = this.awsS3Service.upload(file, this.folderName);
        byId.setBannerIntro(url);
        return this.entityManager.merge(byId);
    }
}
