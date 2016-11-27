package com.woslx.rep.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ApiResult<T> {

    private static final SimplePropertyPreFilter thisFilter = new SimplePropertyPreFilter(
            ApiResult.class, "code", "message", "data","user");

    private int code;
    private String message;
    private T data;
    private UserVO user;

    private SerializeFilter[] filters = new SerializeFilter[2];

    private UserVO getUserVO()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("username");

        if(attribute!=null)
        {
            UserVO vo = new UserVO();
            vo.setUsername((String) attribute);
            return vo;
        }
        return null;
    }

    public ApiResult() {
        this.filters[0] = thisFilter;
        user = getUserVO();
    }

    public ApiResult(int code, String message) {


        this.filters[0] = thisFilter;
        this.code = code;
        this.message = message;
        user = getUserVO();
    }

    public ApiResult(int code, String message, T data) {
        this.filters[0] = thisFilter;
        this.code = code;
        this.message = message;
        this.data = data;
        user = getUserVO();
    }

    public ApiResult(int code, String message, T data, SimplePropertyPreFilter filter) {
        this.filters[0] = thisFilter;
        this.filters[1] = filter;
        this.code = code;
        this.message = message;
        this.data = data;
        user = getUserVO();
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, this.filters);
    }
}
