package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.User;
import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.AuthUserService;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.impl.DefaultAuthUserService;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
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

@WebServlet("/privatePage")
public class PrivatePageServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(PrivatePageServlet.class);
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = authUserService.getUsers();
        List<Book> books = bookService.getBooks();
        req.setAttribute("users", users);
        req.setAttribute("books", books);
        forwardToJsp("privatePage", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("deleteId"));
        if (authUserService.idIsExist(id)) {
            authUserService.removeUser(id);
            log.info("user {} deleted", id);
            redirectToJsp("/privatePage", req, resp);
        }
    }
}
