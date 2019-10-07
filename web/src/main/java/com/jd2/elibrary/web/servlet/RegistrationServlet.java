package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.AuthUserService;
import com.jd2.elibrary.service.impl.DefaultAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    AuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToJsp("registration", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //если пользователя нет в базе, добавляем его
        try {
//            if (!authUserService.userIsExist(login, password)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            authUserService.saveUser(user);
//            }
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        req.setAttribute("login", login);
        forwardToJsp("privatePage", req, resp);
    }
}
