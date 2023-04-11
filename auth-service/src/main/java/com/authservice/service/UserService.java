package com.authservice.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.dto.TokenDto;
import com.authservice.dto.UserDto;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import com.authservice.security.JwtProvider;

import java.util.Optional;

@Service
public class  UserService {

    @Autowired
    UserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public User save(UserDto dto) {
        Optional<User> user = authUserRepository.findByUserName(dto.getUserName());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        User userNew = User.builder()
                .userName(dto.getUserName())
                .password(password)
                .build();
        return authUserRepository.save(userNew);
    }

    public TokenDto login(UserDto dto) {
        Optional<User> user = authUserRepository.findByUserName(dto.getUserName());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}