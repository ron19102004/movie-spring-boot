package com.movie.app.services.impls;

import com.movie.app.entities.Role;
import com.movie.app.entities.User;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.repositories.UserRepository;
import com.movie.app.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<User> find() {
        return this.userRepository.findAll();
    }

    @Override
    public Map<String, Object> changeRole(Long id) {
        User byId = this.findById(id).orElseThrow(() -> new ServiceException("UserNotFound"));
        switch (byId.getRole()) {
            case USER -> byId.setRole(Role.ADMIN);
            case ADMIN -> byId.setRole(Role.USER);
        }
        return this.entityManager.merge(byId).toMapData();
    }
}
