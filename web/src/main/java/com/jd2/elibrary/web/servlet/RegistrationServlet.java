package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Role;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet {
    private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    @Autowired
    AuthUserService authUserService;

    @GetMapping("/registration")
    public String doGet(HttpServletRequest req) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String doPost(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //если пользователя нет в базе, добавляем его
        if (!authUserService.loginIsExist(login)) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.CUSTOMER);
            authUserService.saveUser(user);
            log.info("user {} registered", login);
            req.getSession().setAttribute("login", user);
            return "/customerPage";
        }
            req.setAttribute("error", true);
            log.info("user is not registered");
            return "/registration";
    }
}
