package com.example.demo.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.errors.ResponseError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseError> handlerBadRequestException(BadRequestException bre) {
		ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST, bre.getMessage());
        return ResponseEntity.badRequest().body(responseError);
    }
	
}
