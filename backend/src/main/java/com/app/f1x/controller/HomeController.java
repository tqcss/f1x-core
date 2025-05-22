package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.payload.request.CreateLaundromatRequest;
import com.app.f1x.payload.request.JoinLaundromatRequest;
import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.payload.response.UserDetailsResponse;
import com.app.f1x.service.AppUserService;
import com.app.f1x.service.LaundromatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app")
public class HomeController {

    private final AppUserService appUserService;
    private final LaundromatService laundromatService;

    @Autowired
    public HomeController(AppUserService appUserService, LaundromatService laundromatService) {
        this.appUserService = appUserService;
        this.laundromatService = laundromatService;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        UserDetailsResponse userDetails = appUserService.getUserDetails(authentication.getName());
        LaundromatDetailsResponse laundromatDetails = laundromatService.getLaundromatDetails(authentication.getName());

        LocalDateTime dateTime = LocalDateTime.now();
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        model.addAttribute(new CreateLaundromatRequest());
        model.addAttribute(new JoinLaundromatRequest());

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("laundromatDetails", laundromatDetails);
        model.addAttribute("dateTime", dateTime);
        model.addAttribute("dateString", dateString);


        return "home";
    }

}
