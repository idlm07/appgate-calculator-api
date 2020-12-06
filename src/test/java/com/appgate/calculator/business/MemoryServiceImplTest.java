package com.appgate.calculator.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.appgate.calculator.business.domain.ResponseOperation;
import com.appgate.calculator.exceptions.InvalidOperationException;

@ExtendWith(MockitoExtension.class)
public class MemoryServiceImplTest {

	private static String SESSION_PLUS = "session_+";
	private static String SESSION_MINUS = "session_-";
	private static String SESSION_MULTIPLY = "session_*";
	private static String SESSION_POW = "session_^";
	
	@InjectMocks
	MemoryServiceImpl memory;
	
	@BeforeEach
	public void setUp() throws InvalidOperationException {
		
		memory.create(SESSION_PLUS);
		memory.add(SESSION_PLUS, 2);
		memory.add(SESSION_PLUS, 4);
		memory.add(SESSION_PLUS, 6);
		
		memory.create(SESSION_MINUS);
		memory.add(SESSION_MINUS, 10);
		memory.add(SESSION_MINUS, 5);
		memory.add(SESSION_MINUS, 2);
		
		memory.create(SESSION_MULTIPLY);
		memory.add(SESSION_MULTIPLY, 1);
		memory.add(SESSION_MULTIPLY, 1);
		memory.add(SESSION_MULTIPLY, 5);
		memory.add(SESSION_MULTIPLY, 5);
		
		memory.create(SESSION_POW);
		memory.add(SESSION_POW, 2);
		memory.add(SESSION_POW, 2);
		memory.add(SESSION_POW, 2);
	}
	
	@Test
	public void testCreateShouldBeSuccess() {

		ResponseOperation response = memory.create("sesion_new");
		
		assertEquals(true, response.isStatus(), "Should be true");
		assertEquals("session created", response.getMessage(), "Should be equals");
		
	}
	
	@Test
	public void testAddShouldBeSuccess() throws InvalidOperationException {

		memory.create("sesion_new");
		ResponseOperation response = memory.add("sesion_new", 50);
		
		assertEquals(true, response.isStatus(), "Should be true");
		assertEquals("number added", response.getMessage(), "Should be equals");
		
		List<Integer> numbersAdded = memory.getAll("sesion_new");
		
		assertEquals(Lists.list(50), numbersAdded, "Should have the same values");
	}
	
	@Test
	public void testAddAndGetAllShouldHaveSamesValues() throws InvalidOperationException {

		List<Integer> numbersAdded = memory.getAll(SESSION_MINUS);
		
		assertEquals(Lists.list(10, 5, 2), numbersAdded, "Should have the same values");
	}
	
	@Test
	public void testCalculateAddingShouldHaveGoodResult() throws InvalidOperationException {

		Integer total = memory.calculate(SESSION_PLUS, "+");
		
		assertEquals(12, total, "Should have the same value");
	}
	
	@Test
	public void testCalculateSubstractingShouldHaveGoodResult() throws InvalidOperationException {

		Integer total = memory.calculate(SESSION_MINUS, "-");
		
		assertEquals(3, total, "Should have the same value");
	}
	
	@Test
	public void testCalculateMultiplyingShouldHaveGoodResult() throws InvalidOperationException {

		Integer total = memory.calculate(SESSION_MULTIPLY, "*");
		
		assertEquals(25, total, "Should have the same value");
	}
	
	@Test
	public void testCalculatePowerShouldHaveGoodResult() throws InvalidOperationException {

		Integer total = memory.calculate(SESSION_POW, "^");
		
		assertEquals(16, total, "Should have the same value");
	}

}
