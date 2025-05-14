package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.payload.request.UserRegisterRequest;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.util.enums.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class AccountController {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AccountController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        model.addAttribute(userRegisterRequest);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {
        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword())) {
            bindingResult.addError(new FieldError("userRegisterRequest", "confirmPassword", "Passwords do not match"));
        }

        AppUser appUser = appUserRepository.findAppUserByEmail(userRegisterRequest.getEmail());
        if (appUser != null) {
            bindingResult.addError(new FieldError("userRegisterRequest", "email", "Email is already in use"));
        }

        if (bindingResult.hasErrors()) { return "register"; }

        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            AppUser newAppUser = new AppUser();
            newAppUser.setCreatedAt(LocalDateTime.now());
            newAppUser.setUserRole(UserRole.CLIENT);
            newAppUser.setFirstName(userRegisterRequest.getFirstName());
            newAppUser.setLastName(userRegisterRequest.getLastName());
            newAppUser.setEmail(userRegisterRequest.getEmail());
            newAppUser.setPassword(encoder.encode(userRegisterRequest.getPassword()));

            appUserRepository.save(newAppUser);

            model.addAttribute("userRegisterRequest", new UserRegisterRequest());
            model.addAttribute("success", true);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("userRegisterRequest", "confirmPassword", e.getMessage()));
        }

        return "register";
    }

}
