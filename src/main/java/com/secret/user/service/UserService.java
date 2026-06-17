package com.secret.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secret.user.repository.UserRepository;
import com.secret.user.userDto.UserRegisterDTO;
import com.secret.user.userEntity.UserEntity;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void register(UserRegisterDTO userdata) {

    	UserEntity user = new UserEntity();
    	
    	user.setName(userdata.getName());
    	user.setEmail(userdata.getEmail());
    	user.setPassword(encoder.encode(userdata.getPassword()));

        userRepo.save(user);
    }
}
