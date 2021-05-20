package com.valorant.api.security.controller;

import com.valorant.api.security.model.dto.JwtDto;
import com.valorant.api.security.model.dto.LoginDto;
import com.valorant.api.security.model.dto.UserDto;
import com.valorant.api.security.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Api
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/sign-in")
    public ResponseEntity<?> create(@RequestBody UserDto user){
        if (service.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Correo \""+user.getEmail()+"\" ya existe.");
        }
        if (service.existsByUsername(user.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Nombre de Usuario \""+user.getUsername()+"\" ya existe.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.newUser(user));
    }

    @PostMapping("/log-in")
    public ResponseEntity<JwtDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.Login(loginDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(service.refresh(jwtDto));
    }
}
