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

@Controller
@RequestMapping
public class EditController {
    private static final Logger log = LoggerFactory.getLogger(EditController.class);

    private final UserService userService;

    public EditController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String doGet(HttpServletRequest req) {
        return "/edit";
    }

    @PostMapping("/edit")
    public String doPost(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("login");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        userService.update(user, firstName, lastName, phone);
        log.info("user {} update", user.getId());
        req.getSession().setAttribute("login", user);
        return "redirect:/customerPage";
    }
}
