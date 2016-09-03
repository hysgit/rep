package com.woslx.rep.common;

public interface ExceptionObject {

	String getLogInfo();
	
	String getErrCode();
	
	String getViewInfo();
	
	Integer getCode();
	
	void setLogInfo(String logInfo);
	
	void setViewInfo(String viewInfo);
	
	void setCode(Integer code);
}
