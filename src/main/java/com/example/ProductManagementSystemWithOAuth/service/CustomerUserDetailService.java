package com.example.ProductManagementSystemWithOAuth.service;

import com.example.ProductManagementSystemWithOAuth.model.CustomUserDetail;
import com.example.ProductManagementSystemWithOAuth.model.User;
import com.example.ProductManagementSystemWithOAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("No User Found!!"));
        return user.map(CustomUserDetail::new).get();
    }
}
