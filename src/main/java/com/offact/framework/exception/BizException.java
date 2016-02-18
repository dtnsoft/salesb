package com.offact.framework.exception;

import java.util.List;

import com.offact.framework.constants.SPErrors;

@SuppressWarnings("serial")
public class BizException extends Exception {
	
	private String errorCode;
	private List<String> argList;
	
	private String detailMessage;	

	public BizException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BizException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	public BizException(String errorCode, Throwable arg0) {
		super(arg0);		
		// TODO Auto-generated constructor stub
		this.errorCode = errorCode;
	}
	
	public BizException(String errorCode, String detailMessage) {		
		// TODO Auto-generated constructor stub
		this.errorCode = errorCode;		
		this.detailMessage = detailMessage;
	}
	
	public BizException(SPErrors error) {		
		// TODO Auto-generated constructor stub
		this.errorCode = error.getCode();		
		this.detailMessage = error.getDescription();
	}
	
	public BizException(String errorCode, List<String> argList, Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.errorCode = errorCode;
		this.argList = argList;
	}

	public BizException(String errorCode, List<String> argList, String detailMessage,
			Throwable arg0) {
		
		super(arg0);
		// TODO Auto-generated constructor stub
		this.errorCode = errorCode;
		this.argList = argList;
		this.detailMessage = detailMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public List<String> getArgList() {
		return argList;
	}

	public String getDetailMessage() {
		return detailMessage;
	}
}
