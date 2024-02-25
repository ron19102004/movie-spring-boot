package com.movie.app.services.impls;

import com.movie.app.dto.CreateCountryDto;
import com.movie.app.entities.Country;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.repositories.CountryRepository;
import com.movie.app.services.CountryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
//Transactional là một annotation trong Spring Framework được sử dụng để đánh dấu các phương thức hoặc lớp để quản lý giao dịch trong ứng dụng.
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @PersistenceContext
//    PersistenceContext là một annotation trong Java Persistence API (JPA) được sử dụng để chú thích một trường,
//    thuộc tính hoặc phương thức trong một bean để đánh dấu rằng đối tượng EntityManager sẽ được inject vào đó.
    private EntityManager entityManager;

    @Override
    public Country create(CreateCountryDto createCountryDto) {
        return this.countryRepository.save(Country.builder()
                .name(createCountryDto.getName())
                .build());
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Country byId = this.countryRepository.findById(id).orElse(null);
        if (byId == null) return;
        byId.setDeleted(true);
        this.entityManager.merge(byId);
    }

    @Override
    public Country update(Long id, String name) {
        Country byId = this.countryRepository.findById(id).orElse(null);
        if (byId == null) throw new ServiceException("Country by id is not found");
        byId.setName(name);
        this.entityManager.merge(byId);
        return byId;
    }
}
