package com.example.demo.exception;

public class EmployeeNotFoundException extends Exception {
	private String msg;
	public EmployeeNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
