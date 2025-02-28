package com.example.demo.exception;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class DepartmentNotFoundException extends Exception {
	private String msg;
	public DepartmentNotFoundException(String msg){
		super(msg);
		this.msg=msg;
	}

}
