package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.BookService;
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

@WebServlet(urlPatterns = "/editBookCatalogue")
public class EditBookCatalogueServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    private BookService bookService = DefaultBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        req.setAttribute("books", books);
        forwardToJsp("editBookCatalogue", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
