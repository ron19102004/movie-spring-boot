package com.movie.app.auth;

import com.movie.app.dto.AuthRegisterDto;
import com.movie.app.dto.AuthSignInDto;
import com.movie.app.entities.Role;
import com.movie.app.entities.User;
import com.movie.app.exceptions.ServiceException;
import com.movie.app.repositories.UserRepository;
import com.movie.app.services.JwtService;
import com.movie.app.utils.ValidateCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    User findUser(String username) {
        if (ValidateCustom.isEmail(username))
            return this.userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        else if (ValidateCustom.isPhone(username))
            return this.userRepository.findByPhone(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Phone not found"));
        else return this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Map<String, String> login(AuthSignInDto authSignInDto) {
        User user = findUser(authSignInDto.getUsername());
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), authSignInDto.getPassword())
        );
        String accessToken = jwtService.generate(user);
        return authSignInDto.response(accessToken,"");
    }

    @Override
    public Map<String, Object> register(AuthRegisterDto authRegisterDto) {

        User userEmail = this.userRepository.findByEmail(authRegisterDto.getEmail()).orElse(null);
        if (userEmail != null) throw new ServiceException("Email is existed");
        User userPhone = this.userRepository.findByPhone(authRegisterDto.getPhone()).orElse(null);
        if (userPhone != null) throw new ServiceException("Phone is existed");
        User userUsername = this.userRepository.findByUsername(authRegisterDto.getUsername()).orElse(null);
        if (userUsername != null) throw new ServiceException("Username is existed");

        User user = User.builder()
                .username(authRegisterDto.getUsername())
                .email(authRegisterDto.getEmail())
                .phone(authRegisterDto.getPhone())
                .password(passwordEncoder.encode(authRegisterDto.getPassword()))
                .fullName(authRegisterDto.getFullName())
                .role(Role.USER)
                .build();
        return this.userRepository.save(user).toMapData();
    }
}
