package com.jd2.elibrary.dao.config;

import com.jd2.elibrary.dao.BookDao;
import com.jd2.elibrary.dao.OrderDao;
import com.jd2.elibrary.dao.UserDao;
import com.jd2.elibrary.dao.impl.DefaultBookDao;
import com.jd2.elibrary.dao.impl.DefaultOrderDao;
import com.jd2.elibrary.dao.impl.DefaultUserDao;
import com.jd2.elibrary.dao.repository.BookJpaRepository;
import com.jd2.elibrary.dao.repository.OrderJpaRepository;
import com.jd2.elibrary.dao.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jd2.elibrary.dao.repository")
public class DaoConfig {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(userJpaRepository);
    }

    @Bean
    public BookDao bookDao() {
        return new DefaultBookDao(bookJpaRepository);
    }

    @Bean
    public OrderDao orderDao(){
        return new DefaultOrderDao(orderJpaRepository, userJpaRepository);
    }
}
