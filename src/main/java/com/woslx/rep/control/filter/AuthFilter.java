package com.woslx.rep.control.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hy on 10/12/16.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Object attribute = session.getAttribute("username");
        String requestURI = req.getRequestURI();
        long lastAccessedTime = session.getLastAccessedTime();
        long now = new Date().getTime();
        if(now-lastAccessedTime > session.getMaxInactiveInterval()*1000)
        {
            session.removeAttribute("username");
            attribute=null;
        }
        if((attribute==null))//未登陆
        {
            if((requestURI.equals("/user/login"))
                    ||(requestURI.equals("/statics/admin/login.html"))
                    ||(requestURI.equals("/user/nologin"))
                    ||(requestURI.equals("/favicon.ico"))
                    )
            {
                chain.doFilter(request,response);
            }
            else if(requestURI.startsWith("/statics") &&(!requestURI.endsWith(".html")))
            {
                chain.doFilter(request,response);
            }
            else
            {
                if(requestURI.endsWith(".html"))
                {
                    request.getRequestDispatcher("/statics/admin/login.html").forward(request,response);
                }
                else {
                    request.getRequestDispatcher("/user/nologin").forward(request,response);
                }
            }
        }
        else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
