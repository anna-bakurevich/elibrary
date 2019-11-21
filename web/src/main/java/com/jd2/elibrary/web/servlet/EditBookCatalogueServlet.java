package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.service.impl.BookService;
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
public class EditBookCatalogueServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    @Autowired
    private BookService bookService;

    private int pageNumber = 1;
    private int pageSize = 2;

    @GetMapping("/editBookCatalogue")
    public String doGet(HttpServletRequest req){
        List<Book> books = bookService.getBooks(pageNumber, pageSize);
        int maxNumber = bookService.countPageBooks(pageSize);
        req.setAttribute("books", books);
        req.setAttribute("maxNumber", maxNumber);
        req.setAttribute("pageNumber", pageNumber);
        return "/editBookCatalogue";
    }

    @PostMapping("/editBookCatalogue")
    public String doPost(HttpServletRequest req){

        if (req.getParameter("nextPage") != null) {
            pageNumber++;
            req.setAttribute("pageNumber", pageNumber);
            List<Book> books = bookService.getBooks(pageNumber, pageSize);
            req.setAttribute("books", books);
        }

        if (req.getParameter("prevPage") != null) {
            pageNumber--;
            req.setAttribute("pageNumber", pageNumber);
            List<Book> books = bookService.getBooks(pageNumber, pageSize);
            req.setAttribute("books", books);
        }
        if (req.getParameter("bookDelete") != null) {
            int bookDelete = Integer.parseInt(req.getParameter("bookDelete"));
            int countDelete = Integer.parseInt(req.getParameter("countDelete"));
            bookService.decrCountBook(bookDelete, countDelete);
            log.info("book {} decreased by {}", bookDelete, countDelete);
        }
        if (req.getParameter("bookAdd") != null) {
            int bookAdd = Integer.parseInt(req.getParameter("bookAdd"));
            int countAdd = Integer.parseInt(req.getParameter("countAdd"));
            bookService.incrCountBook(bookAdd, countAdd);
            log.info("book {} increased by {}", bookAdd, countAdd);
        }
        return "redirect:/editBookCatalogue";
    }
}
