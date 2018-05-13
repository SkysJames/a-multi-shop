package com.sky.business.common.vo;

/**
 * 业务类型的错误类
 * @author Sky James
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7053406703455941078L;
	
	private String errorCode = "";
	private String errorMsg = "";

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
		errorMsg = message;
	}
	
	public ServiceException(String errCode, String errMsg) {
		super(errMsg);
		errorCode = errCode;
		errorMsg = errMsg;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
