package com.movie.app.services;

import com.movie.app.dto.CreateConfigDto;
import com.movie.app.entities.Config;
import com.movie.app.interfaces.ServiceCreate;
import com.movie.app.interfaces.ServiceDelete;
import com.movie.app.interfaces.ServiceReader;
import org.springframework.web.multipart.MultipartFile;


public interface ConfigService extends
        ServiceReader<Config, Long>,
        ServiceCreate<Config, CreateConfigDto>,
        ServiceDelete<Long> {
    Config updateIconWeb(Long id, MultipartFile file);
    Config updateBannerIntro(Long id, MultipartFile file);
    void activeConfig(Long id);

}
