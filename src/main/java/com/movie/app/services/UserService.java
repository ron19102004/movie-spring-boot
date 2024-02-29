package com.movie.app.services;

import com.movie.app.entities.User;
import com.movie.app.interfaces.ServiceReader;

import java.util.Map;

public interface UserService extends ServiceReader<User,Long> {
    Map<String,Object> changeRole(Long id);
}
