package com.org.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.org.controller.UserController;

@ControllerAdvice
public class AppExceptionHadler {

private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ExceptionHandler(value = Exception.class)
	public String handleExp(Exception e) {
		String message = e.getMessage();
		logger.error(message);
		return "error";
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public String handleNPE(NullPointerException ne) {
		String message = ne.getMessage();
		logger.error(message);
		return "error";
	}
}
