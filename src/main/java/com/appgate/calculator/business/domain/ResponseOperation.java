package com.appgate.calculator.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseOperation {

	private boolean status;
	private String message;
	private String sessionId;
	private Integer number;
	
}
