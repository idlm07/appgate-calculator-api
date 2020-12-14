package com.appgate.calculator.business.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddNumberRequest implements Serializable{
	
	private static final long serialVersionUID = -3259233551508858019L;
	
	private String sessionId; 
	private Integer number;
	
}
