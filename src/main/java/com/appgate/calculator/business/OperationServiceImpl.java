package com.appgate.calculator.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.appgate.calculator.business.domain.ResponseOperation;

@Service
@Primary
public class OperationServiceImpl implements OperationService{

	@Autowired
	private MemoryService memory;
	
	/**
	 * Constructor
	 * @param memory
	 */
	OperationServiceImpl(MemoryService memory){
		this.memory = memory;
	}
	
	@Override
	public ResponseOperation addNumber(String sessionId, Integer number) {
		return memory.add(sessionId, number);
	}

	@Override
	public ResponseOperation calculate(String sessionId, String operation, boolean continueResult) {
		//Totalize
		ResponseOperation response = memory.calculate(sessionId, operation);

		//Reset memory
		memory.reset(sessionId);
		
		if(continueResult) {
			//add result to session memory
			memory.add(sessionId, response.getNumber());
		}
		
		return response;
	}

}
