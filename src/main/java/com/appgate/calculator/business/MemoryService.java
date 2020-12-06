package com.appgate.calculator.business;

import java.util.List;

import com.appgate.calculator.business.domain.ResponseOperation;
import com.appgate.calculator.exceptions.InvalidOperationException;

public interface MemoryService {

	//Create Session Stack
	public ResponseOperation create(String sessionId);
	
	//Add number
	public ResponseOperation add(String sessionId, Integer number) throws InvalidOperationException;

	//Add number
	public List<Integer> getAll(String sessionId) throws InvalidOperationException;
	
	//Calculate
	public ResponseOperation calculate(String sessionId, String operation) throws InvalidOperationException;

}
