package com.app.f1x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class EmployeeController {

    @GetMapping("/employees")
    public String employees(Model model) {
        return "employees";
    }

}
