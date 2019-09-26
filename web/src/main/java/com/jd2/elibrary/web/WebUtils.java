package com.jd2.elibrary.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    //добавить логирование
    //перенаправление на jsp
    public static void forwardToJsp(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/" + page + ".jsp").forward(request, response);
    }
}
