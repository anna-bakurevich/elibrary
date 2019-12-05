package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String doGet(HttpSession session) {
        Object authUser = session.getAttribute("login");
        if (authUser == null) {
            return "/login";
        }
        return "redirect:/customerPage";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.login(login, password);
        if (user == null) {
            req.setAttribute("error", "login or password invalid");
            return "/registration";
        }
        log.info("user {} logged", user.getLogin());
        req.getSession().setAttribute("login", user);
        if (user.getRole().equals(Role.LIBRARIAN)) {
            return "redirect:/librarianPage";
        }
        return "redirect:/customerPage";
    }
}

