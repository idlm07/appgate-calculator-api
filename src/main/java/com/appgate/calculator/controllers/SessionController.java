package com.appgate.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.calculator.business.SessionService;
import com.appgate.calculator.business.domain.ResponseOperation;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "sessions")
@Api(value = "Session Controller", tags = {"calculator-session-controller"}, description = "Session for calculator")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;

	/**
	 * 
	 * @param sessionService
	 */
	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ResponseOperation> newSession(){
		return new ResponseEntity<>(sessionService.newSession(), HttpStatus.OK);
	}
}
