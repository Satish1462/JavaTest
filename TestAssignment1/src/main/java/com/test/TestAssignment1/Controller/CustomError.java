package com.test.TestAssignment1.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomError implements ErrorController {

	private final Logger app_logger =LogManager.getLogger(this.getClass());
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //do something like logging
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	     
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	app_logger.error("Request Error:",HttpStatus.NOT_FOUND);
	            return "error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	app_logger.error("Request Error:",HttpStatus.INTERNAL_SERVER_ERROR);
	            return "error-500";
	        }
	        else if(statusCode == HttpStatus.BAD_REQUEST.value()){
	        	app_logger.error("Request Error:",HttpStatus.BAD_REQUEST);
	        	return "error-400";
	        }
	    }
	    return "error";
    }
 
	

}
