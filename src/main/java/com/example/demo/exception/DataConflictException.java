package com.example.demo.exception;

public class DataConflictException extends Exception{
	private String mgs;

	public DataConflictException(String mgs) {
		super(mgs);
		this.mgs = mgs;
	}
}
