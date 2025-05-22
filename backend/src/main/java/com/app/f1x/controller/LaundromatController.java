package com.app.f1x.controller;

import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.payload.request.JoinLaundromatRequest;
import com.app.f1x.service.LaundromatService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class LaundromatController {

    private static final Logger logger = LoggerFactory.getLogger(LaundromatController.class);

    private final LaundromatService laundromatService;

    @Autowired
    public LaundromatController(LaundromatService laundromatService) {
        this.laundromatService = laundromatService;
    }

    @PostMapping("/laundromat/create")
    public String createLaundromat(Authentication authentication, @Valid @ModelAttribute CreateLaundromatRequest createLaundromatRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getAllErrors().toString());
            return "redirect:/app/home?createLaundromatFail";
        }

        Boolean success = laundromatService.createLaundromat(authentication.getName(), createLaundromatRequest);
        if (success) {
            return "redirect:/app/home";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/laundromat/generate-invite")
    public String generateInvite(Authentication authentication, Model model) {
        laundromatService.generateLaundromatInvite(authentication.getName());
        return "redirect:/app/employees";
    }

    @PostMapping("/laundromat/join")
    public String joinLaundromat(Authentication authentication, @Valid @ModelAttribute JoinLaundromatRequest joinLaundromatRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getAllErrors().toString());
            return "redirect:/app/home?joinLaundromatFail";
        }

        Boolean success = laundromatService.joinLaundromat(authentication.getName(), joinLaundromatRequest);
        if (success) {
            return "redirect:/app/home";
        } else {
            return "redirect:/app/home?joinLaundromatFail";
        }
    }

}
