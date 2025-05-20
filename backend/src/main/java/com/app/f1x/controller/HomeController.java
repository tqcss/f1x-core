package com.app.f1x.controller;

import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class HomeController {

    private final AppUserService appUserService;

    @Autowired
    public HomeController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        model.addAttribute(new CreateLaundromatRequest());
        model.addAttribute("userInLaundromat", appUserService.userInLaundromat(authentication.getName()));

        return "home";
    }

}
