package com.app.f1x.service.impl;

import com.app.f1x.model.AppUser;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByEmail(email);

        if (appUser == null) { return null; }
        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(String.valueOf(appUser.getUserRole()))
                .build();
    }

}
