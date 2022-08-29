package com.example.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;

	public CustomException() {

	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

	public CustomException(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message: " + this.message + "\n Status: " + this.status;
	}

}
