package com.jd2.elibrary.service.impl.config;

import com.jd2.elibrary.dao.config.DaoConfig;
import com.jd2.elibrary.service.impl.BookService;
import com.jd2.elibrary.service.impl.OrderService;
import com.jd2.elibrary.service.impl.UserService;
import com.jd2.elibrary.service.impl.impl.DefaultBookService;
import com.jd2.elibrary.service.impl.impl.DefaultOrderService;
import com.jd2.elibrary.service.impl.impl.DefaultUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {
    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserService userService() {
        return new DefaultUserService(daoConfig.userDao());
    }

    @Bean
    public BookService bookService() {
     return new DefaultBookService(daoConfig.bookDao());
    }

    @Bean
    public OrderService orderService(){
        return new DefaultOrderService(daoConfig.orderDao());
    }

}
