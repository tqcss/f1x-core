package com.app.f1x.service;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Laundromat;
import com.app.f1x.payload.request.RegisterUserRequest;
import com.app.f1x.payload.response.UserDetailsResponse;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.util.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(username);

        return optionalAppUser.map(appUser -> User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getUserRole().toString())
                .build()).orElse(null);
    }

    private void logIdentityNotFound(String identity) {
        logger.error("Requester not found with identity: {}", identity);
    }

    public Optional<AppUser> findAppUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    public Boolean createAppUser(RegisterUserRequest registerRequest) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            AppUser appUser = new AppUser();

            appUser.setCreatedAt(LocalDateTime.now());
            appUser.setUserRole(UserRole.CLIENT);
            appUser.setFirstName(registerRequest.getFirstName());
            appUser.setLastName(registerRequest.getLastName());
            appUser.setEmail(registerRequest.getEmail());
            appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            appUserRepository.save(appUser);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public Boolean userInLaundromat(String identity) {
        Optional<AppUser> optionalRequester = appUserRepository.findAppUserByEmail(identity);

        if (optionalRequester.isEmpty()) {
            logIdentityNotFound(identity);
            return false;
        }

        AppUser requester = optionalRequester.get();
        return requester.getLaundromat() != null;
    }

    public UserDetailsResponse getUserDetails(String identity) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(identity);

        if (optionalAppUser.isEmpty()) {
            logIdentityNotFound(identity);
            return UserDetailsResponse.builder().build();
        }

        AppUser appUser = optionalAppUser.get();
        Laundromat laundromat = appUser.getLaundromat();

        return UserDetailsResponse.builder()
                .fullName(String.format("%s %s", appUser.getFirstName(), appUser.getLastName()))
                .inLaundromat(laundromat != null)
                .laundromatName(laundromat != null ? laundromat.getName() : null)
                .build();

    }
}
