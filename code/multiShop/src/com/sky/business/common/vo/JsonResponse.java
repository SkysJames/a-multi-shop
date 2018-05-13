package com.sky.business.common.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON返回的对象
 * @author Sky James
 *
 */
public class JsonResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String STATUSCODE_OK = "200";
	public static final String STATUSCODE_CONFIRM = "250";
	public static final String STATUSCODE_ERROR = "300";
	public static final String STATUSCODE_TIMEOUT = "301";
	
	
	private String statusCode = STATUSCODE_OK;
	private String message;
	
	private Map<String, Object> params;
	
	public void setSuccessMessage(String msg) {
		statusCode = STATUSCODE_OK;
		message = msg;
	}
	
	public void setErrorMessage(String msg) {
		statusCode = STATUSCODE_ERROR;
		message = msg;
	}
	
	public void setConfirmMessage(String msg) {
		statusCode = STATUSCODE_CONFIRM;
		message = msg;
	}
	
	public void addParam(String name, Object value) {
		if(params == null) {
			params = new HashMap<String, Object>();
		}
		params.put(name, value);
	}
	
	//Getters and Setters
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
}
