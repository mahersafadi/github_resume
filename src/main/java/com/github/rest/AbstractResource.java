package com.github.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.transferobjects.Error;
import com.github.transferobjects.TransferObject;

public class AbstractResource {
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	@ResponseBody
	public TransferObject defaultExceptionHandler(Exception exception){
		//do not thing
		Error error = new Error();
		error.setErrMsg(exception.getMessage());
		return error;
	}
}
