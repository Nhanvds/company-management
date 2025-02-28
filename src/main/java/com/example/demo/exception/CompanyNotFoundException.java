package com.example.demo.exception;

public class CompanyNotFoundException extends Exception {
	private String msg;
	public CompanyNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
