package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class LibrarianPageServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    @Autowired
    private AuthUserService authUserService;

    @GetMapping("/librarianPage")
    public String doGet(HttpServletRequest req) {
        List<User> users = authUserService.getUsers();
        req.setAttribute("users", users);
        return "/librarianPage";
    }

    @PostMapping("/librarianPage")
    public String doPost(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("deleteId"));
        if (authUserService.idIsExist(userId)) {
            authUserService.removeUser(userId);
            log.info("user {} deleted", userId);
        }
        return "redirect:/librarianPage";
    }
}
