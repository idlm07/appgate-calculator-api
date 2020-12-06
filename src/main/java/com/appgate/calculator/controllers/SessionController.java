package com.appgate.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.calculator.business.SessionServiceImpl;
import com.appgate.calculator.business.domain.ResponseOperation;

@RestController(value = "appgate-calculator/session")
public class SessionController {
	
	@Autowired
	SessionServiceImpl sessionService;
	
	/**
	 * 
	 * @return
	 */
	@PostMapping
	ResponseEntity<ResponseOperation> newSession(){
		return new ResponseEntity<>(sessionService.newSession(), HttpStatus.OK);
	}
}
