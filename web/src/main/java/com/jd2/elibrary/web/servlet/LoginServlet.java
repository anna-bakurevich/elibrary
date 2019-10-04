package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.service.AuthUserService;
import com.jd2.elibrary.service.impl.DefaultAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      forwardToJsp("login", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (authUserService.userIsExist(login, password)) {
            req.setAttribute("authUser", login);
            forwardToJsp("privatePage", req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/registration");
        }
    }
}

