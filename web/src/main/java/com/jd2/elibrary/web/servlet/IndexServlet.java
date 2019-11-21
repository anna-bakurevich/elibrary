package com.jd2.elibrary.web.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class IndexServlet {
    @GetMapping("/index")
    public String doGet(HttpServletRequest req) {
        return "/index";
    }

    @PostMapping("/index")
    public String doPost(HttpServletRequest req) {
        return "redirect:/login";
    }
}
