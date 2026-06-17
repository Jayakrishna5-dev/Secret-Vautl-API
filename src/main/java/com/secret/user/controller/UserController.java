package com.secret.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secret.auth.config.JwtUtil;
import com.secret.user.service.UserService;
import com.secret.user.userDto.UserLoginDTO;
import com.secret.user.userDto.UserRegisterDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@PostMapping("/register")
    public String register(@RequestBody UserRegisterDTO user) {
        service.register(user);
        return "User registered successfully";
    }
	
	@Autowired
    private AuthenticationManager authManager;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@GetMapping("/login")
    public String login(@RequestBody UserLoginDTO request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		request.getEmail(),
                		request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(request.getEmail()); // 🔥 return token
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
