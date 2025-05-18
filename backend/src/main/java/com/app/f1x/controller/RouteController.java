package com.app.f1x.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping({"", "/", "/app", "/app"})
    public String index(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/app/home";
        } else {
            return "redirect:/login";
        }
    }

}
