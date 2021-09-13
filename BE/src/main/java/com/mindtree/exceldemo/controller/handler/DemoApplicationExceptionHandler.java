package com.mindtree.exceldemo.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.exceldemo.dto.ErrorDTO;
import com.mindtree.exceldemo.dto.ResponseDTO;
import com.mindtree.exceldemo.exception.DemoApplicatonException;

@RestControllerAdvice
public class DemoApplicationExceptionHandler {

	@ExceptionHandler(DemoApplicatonException.class)
	public ResponseEntity<ResponseDTO<String>> handleError(DemoApplicatonException ex) {
		return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("Error",
				new ErrorDTO(ex.getMessage(), ex.getCause()), "Error In Application", false), HttpStatus.BAD_REQUEST);
	}

}
