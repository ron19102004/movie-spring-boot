package com.movie.app.services.impls;

import com.movie.app.aws.AwsS3Service;
import com.movie.app.entities.Category;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.repositories.CategoryRepository;
import com.movie.app.services.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final String folderImage = "category_image";
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AwsS3Service awsS3Service;

    @Override
    public Category create(String name, MultipartFile file) {
        String image = this.awsS3Service.upload(file, this.folderImage);
        Category category = Category.builder()
                .name(name)
                .image(image)
                .deleted(false)
                .build();
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> find() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category updateImage(Long id, MultipartFile file) {
        Category byId = this.categoryRepository.findById(id).orElse(null);
        if(byId == null) throw new ServiceException("Category is not found");
        String image = this.awsS3Service.upload(file,this.folderImage);
        String[] imageById = byId.getImage().split("/");
        String imageByIdName = imageById[imageById.length-1];
        this.awsS3Service.delete(imageByIdName,this.folderImage);
        byId.setImage(image);
        this.entityManager.merge(byId);
        return byId;
    }
}
