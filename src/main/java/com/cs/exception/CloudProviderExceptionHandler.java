package com.cs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CloudProviderExceptionHandler 
{
	
	 @ExceptionHandler(value = {CloudProviderNotFoundException.class})
	    public ResponseEntity<Object> handleCloudProviderNotFoundException
	            (CloudProviderNotFoundException CloudProviderNotFoundException)
	    {
	        CloudProviderException CloudProviderException = new CloudProviderException(
	                CloudProviderNotFoundException.getMessage(),
	                CloudProviderNotFoundException.getCause(),
	                HttpStatus.NOT_FOUND
	        );

	        return new ResponseEntity<>(CloudProviderException, HttpStatus.NOT_FOUND);
	    }
}
