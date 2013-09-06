package com.casinogod.webinterface;

public class ErrorResponse {
	private String errorAction = "";
	private String errorMessage = "";
	private int	errorCode = 0;
	
	public String getErrorAction() {
		return errorAction;
	}
	public void setErrorAction(String errorAction) {
		this.errorAction = errorAction;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	
}
