package com.jd2.elibrary.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private static final String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);

        //достаем локаль из запроса
        String locale = request.getParameter("locale");
        //если локаль не определена, пытаемся достать ее из сессии
        if (locale == null) {
            locale = (String) request.getSession().getAttribute("locale");
        }
        //если и в запросе и в сессии локаль не определена, устанавливаем русскую, иначе - текущую
        if (locale == null) {
            request.getSession().setAttribute("locale", "ru_RU");
        } else {
            request.getSession().setAttribute("locale", locale);
        }
        filterChain.doFilter(request, response);
    }
}
