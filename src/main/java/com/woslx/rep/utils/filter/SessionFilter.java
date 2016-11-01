package com.woslx.rep.utils.filter;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hy on 10/31/16.
 */
public class SessionFilter implements Filter {

    private JedisPool pool;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pool = AccessTokenTool.createRedisPool();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = rq.getRequestURI();
        if(!requestURI.startsWith("/statics")) {
            DistributedSession distributedSession = DistributedSession.getDistributedSession(pool, rq);
            distributedSession.resetTimeOut();//重置过期时间
            rq.setAttribute("distributedSession", distributedSession);
            if (distributedSession.isNewCreate()) {
                Cookie cookie = new Cookie("distributed-session", distributedSession.getSessionId());
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        
        chain.doFilter(rq,response);


    }

    @Override
    public void destroy() {

    }
}
