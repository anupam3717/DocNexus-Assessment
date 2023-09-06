package com.smart.DocNexusAssessment.controllers;

import com.smart.DocNexusAssessment.dto.JwtResponse;
import com.smart.DocNexusAssessment.dto.User;
import com.smart.DocNexusAssessment.security.JwtProvider;
import com.smart.DocNexusAssessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Auth {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwt;
    @Autowired
    private AuthenticationManager authManager;
    @PostMapping("signup")
    public String signup(@RequestBody User user){

        userService.addUser(user);

        return "signup successful";
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user){

        Authentication x = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String token= jwt.generateToken(user.getEmail());
        JwtResponse response = JwtResponse.builder().message("Login successful").token(token).build();
        return  ResponseEntity.ok().body(response);
    }
}
