package com.movie.app.services;

import com.movie.app.entities.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    Category create(String name, MultipartFile file);
    List<Category> find();
    Category updateImage(Long id,MultipartFile file);
}
