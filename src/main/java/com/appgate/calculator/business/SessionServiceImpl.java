package com.appgate.calculator.business;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.appgate.calculator.business.domain.ResponseOperation;
import com.appgate.calculator.exceptions.InvalidOperationException;

@Service
@Primary
public class SessionServiceImpl implements SessionService{

	private static final String ERROR_CREATING_NEW_SESSION = "Error creating new session";
	private static final Logger logHandler = LoggerFactory.getLogger(SessionServiceImpl.class);
	 
	
	@Autowired
	private MemoryService memory;
	
	@Override
	public ResponseOperation newSession() {
		ResponseOperation response;
		try {
			//generate sessionId
			String sessionId = SessionGenerator.newSession();
			
			//create memory slot
			response = memory.create(sessionId);
			
			//log handler
			logHandler.info("New Session created: {}", response);
			
		} catch (NoSuchAlgorithmException e) {
			throw new InvalidOperationException(ERROR_CREATING_NEW_SESSION);
		}
		
		return response;
	}

}
