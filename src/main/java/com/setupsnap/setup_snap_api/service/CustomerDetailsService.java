package com.setupsnap.setup_snap_api.service;

import com.setupsnap.setup_snap_api.model.AuthEntity;
import com.setupsnap.setup_snap_api.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final AuthRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email){
        AuthEntity auth = repo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("user not found by email"));

        return org.springframework.security.core.userdetails.User
                .withUsername(auth.getEmail())
                .password(auth.getPassword())
                .authorities(auth.getRoles().toArray(new String[0]))
                .build();
    }
}
