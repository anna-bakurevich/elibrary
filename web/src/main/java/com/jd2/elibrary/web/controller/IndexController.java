package com.jd2.elibrary.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/index")
    public String doGet() {
        return "index";
    }

    @PostMapping("/index")
    public String doPost() {
        return "redirect:/login";
    }
}
