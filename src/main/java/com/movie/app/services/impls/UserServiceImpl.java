package com.movie.app.services.impls;

import com.movie.app.entities.User;
import com.movie.app.repositories.UserRepository;
import com.movie.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

}
