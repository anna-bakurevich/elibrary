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
import java.util.List;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;
import static com.jd2.elibrary.web.WebUtils.redirectToJsp;

@WebServlet("/librarianPage")
public class LibrarianPageServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = authUserService.getUsers();
        req.setAttribute("users", users);
        forwardToJsp("librarianPage", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("deleteId"));
        if (authUserService.idIsExist(userId)) {
            authUserService.removeUser(userId);
            log.info("user {} deleted", userId);
            redirectToJsp("/librarianPage", req, resp);
        }
    }
}
