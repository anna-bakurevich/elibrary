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
import static com.jd2.elibrary.web.WebUtils.redirectToJsp;

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

        if (req.getParameter("bookDelete") != null) {
            int bookDelete = Integer.parseInt(req.getParameter("bookDelete"));
            int countDelete = Integer.parseInt(req.getParameter("countDelete"));
            bookService.decreaseCountBook(bookDelete, countDelete);
            log.info("book {} decreased by {}", bookDelete, countDelete);
        }
        if (req.getParameter("bookAdd") != null) {
            int bookAdd = Integer.parseInt(req.getParameter("bookAdd"));
            int countAdd = Integer.parseInt(req.getParameter("countAdd"));
            bookService.increaseCountBook(bookAdd, countAdd);
            log.info("book {} increased by {}", bookAdd, countAdd);
        }
        redirectToJsp("/editBookCatalogue", req, resp);
//        forwardToJsp("librarianPage", req, resp);
    }
}
