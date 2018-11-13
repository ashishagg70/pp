package com.infy.isa.exception;

@SuppressWarnings("serial")
public class InvalidEmailOrDateOfBirth extends Exception{
	public InvalidEmailOrDateOfBirth(String message) {
		super(message);
	}
}
