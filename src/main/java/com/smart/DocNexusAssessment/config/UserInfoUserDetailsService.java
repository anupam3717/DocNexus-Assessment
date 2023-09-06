package com.smart.DocNexusAssessment.config;


import com.smart.DocNexusAssessment.entity.UserEntity;
import com.smart.DocNexusAssessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity x = userService.findByEmail(email);
      ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return new User(x.getEmail(),x.getPassword(),authorities);
    }
}
