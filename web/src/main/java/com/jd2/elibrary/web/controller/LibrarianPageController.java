package com.jd2.elibrary.web.controller;

import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class LibrarianPageController {
    private static final Logger log = LoggerFactory.getLogger(LibrarianPageController.class);

    private final UserService userService;

    public LibrarianPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/librarianPage")
    public String doGet(HttpServletRequest req) {
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        return "/librarianPage";
    }

    @PostMapping("/librarianPage")
    public String doPost(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("deleteId"));
        if (userService.existsById(userId)) {
            userService.deleteById(userId);
            log.info("user {} deleted", userId);
        }
        return "redirect:/librarianPage";
    }
}
