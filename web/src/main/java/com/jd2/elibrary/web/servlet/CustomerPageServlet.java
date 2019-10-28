package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.AuthUserService;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import com.jd2.elibrary.service.impl.impl.DefaultAuthUserService;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import com.jd2.elibrary.service.impl.impl.DefaultOrderService;
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

@WebServlet("/customerPage")
public class CustomerPageServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks();
        req.setAttribute("books", books);
        forwardToJsp("customerPage", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int bookId = Integer.parseInt((req.getParameter("bookToOrder")));
        int count = bookService.getById(bookId).getCount();
        User user = (User) req.getSession().getAttribute("login");
       if (count > 0) {
         if (orderService.orderIsExist(user.getId())) {
                //добавить книгу в существующий заказ по bookId
            //иначе создать новый заказ и добавляем в него книгу
            } else {
                orderService.saveOrder;
            }
       } else {
           //вывести сообщение о недоступности книги для заказа
       }

        log.info("order {} created", orderId);
        redirectToJsp("/customerPage", req, resp);
    }
}


