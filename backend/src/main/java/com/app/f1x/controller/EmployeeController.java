package com.app.f1x.controller;

import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.service.LaundromatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class EmployeeController {

    private final LaundromatService laundromatService;

    @Autowired
    public EmployeeController(LaundromatService laundromatService) {
        this.laundromatService = laundromatService;
    }

    @GetMapping("/employees")
    public String employees(Authentication authentication, Model model) {
        LaundromatDetailsResponse laundromatDetails = laundromatService.getLaundromatDetails(authentication.getName());

        model.addAttribute("laundromatDetails", laundromatDetails);

        return "employees";
    }

}
