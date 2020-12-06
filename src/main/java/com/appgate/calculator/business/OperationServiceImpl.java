package com.appgate.calculator.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appgate.calculator.business.domain.ResponseOperation;

@Service
public class OperationServiceImpl implements OperationService{

	@Autowired
	private MemoryService memory;
	
	@Override
	public ResponseOperation addNumber(String sessionId, Integer number) {
		return memory.add(sessionId, number);
	}

	@Override
	public ResponseOperation calculate(String sessionId, String operation) {
		return memory.calculate(sessionId, operation);
	}

}
