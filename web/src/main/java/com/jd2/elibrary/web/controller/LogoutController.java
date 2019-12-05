package com.jd2.elibrary.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LogoutController {

    @GetMapping("/logout")
   public String doGet(HttpServletRequest req) {
        //удаление объекта с указанным именем из сессии
        req.getSession().removeAttribute("login");
        //установка сессии недействительной и отмена привязки объектов, связанных с ней
        req.getSession().invalidate();
        //перенаправление на страницу логина
        return "/login";
    }
}
