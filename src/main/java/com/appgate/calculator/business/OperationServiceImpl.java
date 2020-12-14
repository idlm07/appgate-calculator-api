package com.appgate.calculator.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.appgate.calculator.business.domain.ResponseOperation;

@Service
@Primary
public class OperationServiceImpl implements OperationService{
	
	private static final Logger logHandler = LoggerFactory.getLogger(OperationServiceImpl.class);

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
		
		//Add number to memory
		ResponseOperation response = memory.add(sessionId, number);
		
		//log handler
		logHandler.info("Number Added: {}", response);
		
		return response;
	}

	@Override
	public ResponseOperation calculate(String sessionId, String operation, boolean continueResult) {
		//Totalize
		ResponseOperation response = memory.calculate(sessionId, operation);
		
		//log handler
		logHandler.info("Calculated: {}", response);

		//Reset memory
		memory.reset(sessionId);
		
		if(continueResult) {
			//add result to session memory
			memory.add(sessionId, response.getNumber());
			
			//log handler
			logHandler.info("Result Added.");
		}
		
		return response;
	}

}
