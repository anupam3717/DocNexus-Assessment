package com.smart.DocNexusAssessment.service;

import com.smart.DocNexusAssessment.dto.User;
import com.smart.DocNexusAssessment.entity.BlogEntity;
import com.smart.DocNexusAssessment.entity.UserEntity;
import com.smart.DocNexusAssessment.rapo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public String addUser(User user){
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail(user.getEmail());
        String password=encoder.encode(user.getPassword());
        userEntity.setPassword(password);
        userRepository.save(userEntity);
        return "added";
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
