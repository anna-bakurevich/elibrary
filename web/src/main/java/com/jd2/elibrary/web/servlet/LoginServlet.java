package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.AuthUserService;
import com.jd2.elibrary.service.impl.impl.DefaultAuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;
import static com.jd2.elibrary.web.WebUtils.redirectToJsp;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToJsp("login", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = authUserService.login(login, password);
        if (user == null) {
            req.setAttribute("error", "login or password invalid");
            redirectToJsp("/registration", req, resp);
            return;
        }
        log.info("user {} logged", user.getLogin());
        req.getSession().setAttribute("login", user);
        redirectToJsp("/privatePage", req, resp);
    }
}

