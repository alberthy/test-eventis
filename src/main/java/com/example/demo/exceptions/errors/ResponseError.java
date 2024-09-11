package com.example.demo.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {
	
	private final String message;
	private final String description;
	
	public ResponseError(HttpStatus status, String description) {
		this(status.name(), description);
	}
	
	public static ResponseEntity<ObjectResponse> internalServerError(HttpStatus httpStatus, ObjectResponse objectResponse) {
        return ResponseEntity.status((httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus)).body(objectResponse);
    }
	
}
