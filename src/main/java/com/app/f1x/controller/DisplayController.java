package com.app.f1x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayController {

    @GetMapping("/home")
    public String home(Model model) {
        // model.addAttribute("message", "Hello World");
        return "home";
    }

    @GetMapping("/order")
    public String order(Model model) {
        return "order";
    }

}
