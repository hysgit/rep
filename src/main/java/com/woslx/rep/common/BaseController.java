package com.woslx.rep.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exception(Exception e, HttpServletRequest request) {
		// ApiException
		logger.error("have exception", e);
		if (e instanceof ApiException) {
			return e.toString();
		} else if(e instanceof BaseException){
			return ((BaseException) e).getApiMessage();
		}else { // unknown exception
			logger.error("unknown exception", e);
			JSONObject error = new JSONObject();
			error.put("code", "-1");
			error.put("message", e.toString());
			return error.toJSONString();
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
	
	protected String retrunResult(String result,String jsonpCallback){
		if(StringUtils.isBlank(jsonpCallback)){
			return result;
		}else{
			return jsonpCallback + "(" + result + ")";
		}
	}
	
	
}