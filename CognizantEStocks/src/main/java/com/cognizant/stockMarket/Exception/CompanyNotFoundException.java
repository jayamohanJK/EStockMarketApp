package com.cognizant.stockMarket.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Company doesn't Exist")
public class CompanyNotFoundException extends Exception{

	
	public CompanyNotFoundException(String message) {
		super(message);
	}

	

	
	
}
