package com.movie.app.services;

import com.movie.app.entities.Category;
import com.movie.app.interfaces.ServiceDelete;
import com.movie.app.interfaces.ServiceReader;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService extends
        ServiceReader<Category, Long>,
        ServiceDelete<Long> {
    Category create(String name, MultipartFile file);

    Category updateImage(Long id, MultipartFile file);
}
