package com.employeeservice.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employeeservice.exception.ValidationException;
import com.employeeservice.model.ErrorResponse;

@RestControllerAdvice
public class EmployeeControllerException {

	@ExceptionHandler(ValidationException.class)
	ResponseEntity<ErrorResponse> handlerValidationException(ValidationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getErrorCode(),
				exception.getErrorMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

}
