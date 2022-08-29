package com.example.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	Date currentDate = new Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	// dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException e) {
		CustomException customException = new CustomException();
		customException.setMessage(e.getMessage());
		customException.setStatus(e.getStatus());
		return new ResponseEntity<>(customException, e.getStatus());
	}

//
//	@ExceptionHandler(value = CustomBadRequestException.class)
//	public ResponseEntity<Object> handleAlreadyExistsException(CustomBadRequestException e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}

}
