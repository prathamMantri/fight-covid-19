package com.pmantri.lockdownvalidukan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping({
            "/",
            "/login"
    })
    public String index(Model model) {
        return "index";
    }

}
