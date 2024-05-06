package com.employeeservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorMessage;

	public ValidationException(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
