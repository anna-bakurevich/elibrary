package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //удаление объекта с указанным именем из сессии
        req.getSession().removeAttribute("authUser");
        //установка сессии недействительной и отмена привязки объектов, связанных с ней
        req.getSession().invalidate();
        //перенаправление на страницу логина
        WebUtils.forwardToJsp("login", req, resp);
    }
}
