package com.authservice.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.authservice.dto.TokenDto;
import com.authservice.dto.UserDto;
import com.authservice.entity.User;
import com.authservice.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto dto){
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = authUserService.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDto dto){
        User authUser = authUserService.save(dto);
        if(authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
}