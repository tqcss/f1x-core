package com.app.f1x.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class routeController {

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard() {
        return "dashboard";
    }

}
