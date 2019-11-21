package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
import com.jd2.elibrary.model.User;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class CustomerPageServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    private int pageNumber = 1;
    private int pageSize = 2;

    @GetMapping("/customerPage")
    public String doGet(HttpServletRequest req) {
        List<Book> books = bookService.getBooks(pageNumber, pageSize);
        int maxNumber = bookService.countPageBooks(pageSize);
        req.setAttribute("books", books);
        req.setAttribute("maxNumber", maxNumber);
        req.setAttribute("pageNumber", pageNumber);
        return "/customerPage";
    }

    @PostMapping("/customerPage")
    public String doPost(HttpServletRequest req) {

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

        if (req.getParameter("bookToOrder") != null) {
            int bookToOrder = Integer.parseInt(req.getParameter("bookToOrder"));
            int countToOrder = Integer.parseInt(req.getParameter("countToOrder"));
            int count = bookService.getById(bookToOrder).getCount();
            User user = (User) req.getSession().getAttribute("login");
            //пытаемся получить оформляющийся заказ
            Order orderFilled = orderService.getOrderFilledByUserId(user.getId());

            if (count > 0) {
                if (orderFilled != null) {
                    //добавляем в него книгу по bookId (обновляем заказ)
                    orderService.updateOrder(orderFilled, bookToOrder);
                    log.info("user {} update order {} at {}", user.getId(), orderFilled.getId(), LocalDateTime.now());
                    //надо уменьшить кол-во этой книги на 1
                    //иначе создаем новый заказ и добавляем в него книгу
                } else {
                    orderFilled = new Order();
                    orderFilled.setUser(user);
                    orderFilled.setOrderDate(LocalDate.now());
                    orderFilled.setReturnDate(LocalDate.now().plusDays(30));
                    orderFilled.setOrderStatus(OrderStatus.FILLED);
                    orderService.saveOrder(orderFilled);
                    log.info("user {} created order {} at {}", user.getId(), orderFilled.getId(), LocalDateTime.now());
                    //добавляем книгу в новый заказ и уменьшаем кол-во этой книги на 1
                }
            } else {
                //вывести сообщение о недоступности книги для заказа
            }
        }
        return "redirect:/customerPage";
    }
}


