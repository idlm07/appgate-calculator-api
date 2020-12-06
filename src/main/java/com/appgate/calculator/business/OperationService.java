package com.appgate.calculator.business;

import com.appgate.calculator.business.domain.ResponseOperation;

public interface OperationService {
	
	public ResponseOperation addNumber(String sessionId, Integer number);
	
	public ResponseOperation calculate(String sessionId, String operation);

}
