package com.appgate.calculator.business.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sessionId;
	private String operation;
	private boolean continueResult;
	
}
