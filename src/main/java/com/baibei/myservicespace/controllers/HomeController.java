package com.baibei.myservicespace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/future-feature")
    public String futureFeature() {
        return "redirect:/under-construction";
    }

    @GetMapping("/under-construction")
    public String underConstruction() {
        return "under-construction";
    }
}
