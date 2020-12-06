package com.appgate.calculator.business;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SessionGenerator {
	
	private static final String ALGORITHM = "SHA1PRNG";

	/**
	 * Empty private contructor
	 */
	private SessionGenerator() {
		throw new IllegalAccessError("This class can't be instantiate");
	}
	
	/**
	 * Generate unique sessionId
	 * @return unique sessionId
	 * @throws NoSuchAlgorithmException
	 */
	public static String newSession() throws NoSuchAlgorithmException {
		
		SecureRandom secure = SecureRandom.getInstance(ALGORITHM);
		Long sessionId = secure.nextLong();
		
		return sessionId.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(SessionGenerator.newSession());
	}
}
