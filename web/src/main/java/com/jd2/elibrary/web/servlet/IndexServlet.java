package com.jd2.elibrary.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;
import static com.jd2.elibrary.web.WebUtils.redirectToJsp;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToJsp("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToJsp("/login", req, resp);
    }
}
