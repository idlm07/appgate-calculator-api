package com.appgate.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.calculator.business.OperationService;
import com.appgate.calculator.business.domain.AddNumberRequest;
import com.appgate.calculator.business.domain.CalculateRequest;
import com.appgate.calculator.business.domain.ResponseOperation;

@RestController
@RequestMapping(path = "operations")
public class OperationController {

	@Autowired
	private OperationService operationService;

	/**
	 * Constructor
	 * 
	 * @param sessionService
	 */
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}

	/**
	 * Add number to calculator memory.
	 * 
	 * @return Response data
	 */
	@PostMapping(path = "/number")
	ResponseEntity<ResponseOperation> addNumber(@RequestBody AddNumberRequest request) {
		return new ResponseEntity<>(operationService.addNumber(request.getSessionId(), request.getNumber()),
				HttpStatus.OK);
	}

	/**
	 * Calculate all the numbers with the operation.
	 * 
	 * @return Result of the operation
	 */
	@PostMapping(path = "/calculate")
	ResponseEntity<ResponseOperation> calculate(@RequestBody CalculateRequest request) {
		return new ResponseEntity<>(
				operationService.calculate(request.getSessionId(), request.getOperation(), request.isContinueResult()),
				HttpStatus.OK);
	}
}
