package com.app.f1x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class OrdersController {
    @GetMapping({"order"})
    public String order() {
        return "order";
    }
}
