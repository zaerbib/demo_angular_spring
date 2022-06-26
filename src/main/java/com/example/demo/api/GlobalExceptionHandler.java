package com.example.demo.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.OperationResponse;
import com.example.demo.model.response.OperationResponse.ResponseStatusEnum;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public OperationResponse handleBaseException(DataIntegrityViolationException e){
		OperationResponse resp = new OperationResponse();
		resp.setOperationStatus(ResponseStatusEnum.ERROR);
		resp.setOperationMessage(e.getRootCause().getMessage());
		return resp;
	}
}
