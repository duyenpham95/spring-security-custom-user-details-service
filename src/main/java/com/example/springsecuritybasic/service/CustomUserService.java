package com.example.springsecuritybasic.service;

import com.example.springsecuritybasic.model.CustomUser;
import com.example.springsecuritybasic.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<CustomUser> customUsers = customUserRepository.findByEmail(username);
        if (customUsers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for user " + username);
        }
        return customUsers.get(0);
    }
}
