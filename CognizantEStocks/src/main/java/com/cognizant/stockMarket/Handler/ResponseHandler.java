package com.cognizant.stockMarket.Handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> genereteResponse(String msg,HttpStatus httpStatus,Object obj)
	{
		
		Map<String,Object> resMp= new HashMap<>();
		resMp.put("message", msg);
		resMp.put("status code", httpStatus.value());
		if(obj!=null)
		resMp.put("data", obj);
		ResponseEntity<Object> res= new ResponseEntity<Object>(obj,httpStatus);
		return res;
	}
	
	
}
