package com.appgate.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.calculator.business.OperationService;
import com.appgate.calculator.business.domain.ResponseOperation;

@RestController(value = "/appgate-calculator/operation")
public class OperationController {
	
	@Autowired
	OperationService sessionService;
	
	/**
	 * 
	 * @return
	 */
	@PostMapping(path = "/number")
	ResponseEntity<ResponseOperation> addNumber(String sessionId, Integer number){
		return new ResponseEntity<>(sessionService.addNumber(sessionId, number), HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping(path = "/calculate")
	ResponseEntity<ResponseOperation> calculate(String sessionId, String operation, boolean continueResult){
		return new ResponseEntity<>(sessionService.calculate(sessionId, operation, continueResult), HttpStatus.OK);
	}
}
