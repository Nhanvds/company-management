package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	// xu ly loi ko tim thay cty
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Object> handleCompanyNotFoundException(CompanyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// xu ly loi ko tim thay employee
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

	//xu ly loi ko tim thay department
//	@ExceptionHandler(DepartmentNotFoundException.class)
//	public ResponseEntity<Object> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
//		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
//	}

	//khi ko co exceptionHandler cua exception xay ra thi se dc xu ly boi 1 trong 2 exception sau:

	// xu ly loi chung (RuntimeException)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handlerRuntimeException (RuntimeException exception, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Co loi xay ra, vui long thu lai!");

		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// xu ly loi chung (Exception)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception exception,  WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message",exception.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}



}
