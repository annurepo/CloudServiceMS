package com.cs.exception;


public class CloudProviderNotFoundException extends RuntimeException {

	public CloudProviderNotFoundException(String message) {
		super(message);
		
	}
	
	public CloudProviderNotFoundException(String message, Throwable cause) {
		super(message,cause);
		
	}
}
