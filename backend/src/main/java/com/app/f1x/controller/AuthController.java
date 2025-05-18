package com.app.f1x.controller;

import com.app.f1x.payload.request.RegisterUserRequest;
import com.app.f1x.service.AppUserService;
import com.app.f1x.util.AuthUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AppUserService appUserService;

    @Autowired
    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();

        model.addAttribute(registerUserRequest);
        model.addAttribute("success", false); // keep track of account creation status

        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterUserRequest registerUserRequest, BindingResult result) {
        if (!AuthUtil.passwordsMatch(registerUserRequest)) {
            result.addError(new FieldError("registerUserRequest", "confirmPassword", "passwords do not match"));
        }

        if (appUserService.findAppUserByEmail(registerUserRequest.getEmail()).isPresent()) {
            result.addError(new FieldError("registerUserRequest", "email", "email address already in use"));
        }

        if (result.hasErrors()) {
            logger.error(result.getAllErrors().toString());
            return "register";
        }

        Boolean accountCreationSuccess = appUserService.createAppUser(registerUserRequest);
        model.addAttribute("success", accountCreationSuccess);
        model.addAttribute(new RegisterUserRequest()); // reset object fields

        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

}
