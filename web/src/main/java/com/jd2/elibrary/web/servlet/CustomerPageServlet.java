package com.jd2.elibrary.web.servlet;

import com.jd2.elibrary.model.Book;
import com.jd2.elibrary.model.Order;
import com.jd2.elibrary.model.OrderStatus;
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
import java.time.LocalDate;
import java.util.List;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;
import static com.jd2.elibrary.web.WebUtils.redirectToJsp;

@WebServlet("/customerPage")
public class CustomerPageServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomerPageServlet.class);
    private AuthUserService authUserService = DefaultAuthUserService.getInstance();
    private BookService bookService = DefaultBookService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();
    private int pageNumber = 1;
    private int pageSize = 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getBooks(pageNumber, pageSize);
        int maxNumber = bookService.countPageBooks(pageSize);
        req.setAttribute("books", books);
        req.setAttribute("maxNumber", maxNumber);
        req.setAttribute("pageNumber", pageNumber);
        forwardToJsp("customerPage", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                    //иначе создаем новый заказ и добавляем в него книгу
                } else {
                    orderFilled = new Order();
                    orderFilled.setUser(user);
                    orderFilled.setOrderDate(LocalDate.now());
                    orderFilled.setReturnDate(LocalDate.now().plusDays(30));
                    orderFilled.setOrderStatus(OrderStatus.FILLED);
                    orderService.saveOrder(orderFilled);
                }
            } else {
                //вывести сообщение о недоступности книги для заказа
            }
        }

        // log.info("order {} created", orderId);
        redirectToJsp("/customerPage", req, resp);
    }
}


