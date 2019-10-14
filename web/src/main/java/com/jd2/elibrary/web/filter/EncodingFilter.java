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

        String locale = request.getParameter("locale");

        if (locale==null){
                request.getSession().setAttribute("locale", "ru_RU");
        }else {
            request.getSession().setAttribute("locale", locale);
        }
        filterChain.doFilter(request, response);
    }
}
