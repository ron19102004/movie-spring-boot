package com.movie.app.auth;

import com.movie.app.dto.AuthRegisterDto;
import com.movie.app.dto.AuthSignInDto;
import com.movie.app.exceptions.ServiceException;

import java.util.Map;

public interface AuthService {
    Map<String,Object> register(AuthRegisterDto authRegisterDto);
    Map<String,String> login(AuthSignInDto authSignInDto);
}
