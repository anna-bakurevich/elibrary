
package com.jd2.elibrary.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jd2.elibrary.web.WebUtils.forwardToJsp;

@WebFilter("/index")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        //приводим запрос и ответ к http для дальнейшего использования
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object authUser = request.getSession().getAttribute("login");

        //если пользователя нет в системе перенаправляем его на страницу логина
        if (authUser == null) {
            forwardToJsp("login", request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
