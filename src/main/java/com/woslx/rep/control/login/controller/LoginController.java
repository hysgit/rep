package com.woslx.rep.control.login.controller;

import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.Constants;
import com.woslx.rep.control.login.entity.UserLogin;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.param.ParamItem;
import com.woslx.rep.utils.AppUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hy on 10/12/16.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    /**
     * @return
     */
    @RequestMapping(value = "/login",consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@RequestBody UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) {

        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        if(StringUtils.isEmpty(userLogin.getUserName()) || StringUtils.isEmpty(userLogin.getPassword())) {
            apiResult.setCode(1);
            apiResult.setMessage("登录失败");
            apiResult.setData("/statics/admin/login.html?_t="+new Date().getTime());
            //response.setHeader("refresh","0;URL=/statics/admin/login.html");
            return apiResult.toString();
        }
        else {
            String userName = userLogin.getUserName();
            String password = userLogin.getPassword().toLowerCase();
            String md5 = AppUtils.MD5("wyy1314").toLowerCase();
            String md5admin = AppUtils.MD5("xyz123").toLowerCase();
            if(userName.equals("wyy") &&(md5.equals(password)))
            {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(1800);
                session.setAttribute("username", "wyy");
                apiResult.setCode(0);
                apiResult.setMessage("登录成功");
                apiResult.setData("/index.html?_t="+new Date().getTime());
            }
            else if(userName.equals("admin") &&(md5admin.equals(password)))
            {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(1800);
                session.setAttribute("username", "admin");
                apiResult.setCode(0);
                apiResult.setMessage("登录成功");
                apiResult.setData("/index.html?_t="+new Date().getTime());
            }
            else {
                apiResult.setCode(1);
                apiResult.setMessage("登录失败");
                apiResult.setData("/statics/admin/login.html?_t="+new Date().getTime());
            }

            return apiResult.toString();
        }
    }

    @RequestMapping(value = "/logout",consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request) {

        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        apiResult.setCode(0);
        apiResult.setMessage("注销成功");
        apiResult.setData("/statics/admin/login.html?_t="+new Date().getTime());

        return apiResult.toString();
    }

    @RequestMapping(value = "/get/login",consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getLogin() {

        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        if(apiResult.getUser() == null)
        {
            apiResult.setCode(1000);
            apiResult.setMessage("未登录");
            apiResult.setData("/statics/admin/login.html?_t="+new Date().getTime());
        }

        return apiResult.toString();
    }

    @RequestMapping(value = "/nologin",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String nologin() {

        ApiResult<String> apiResult = new ApiResult<>(1000, "你已经超时或未登陆，请重新登陆","/statics/admin/login.html?_t="+new Date().getTime());

        return apiResult.toString();
    }
}
