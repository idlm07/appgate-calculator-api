package com.appgate.calculator.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.appgate.calculator.business.domain.ResponseOperation;
import com.appgate.calculator.exceptions.InvalidOperationException;
import com.appgate.calculator.util.Messages;

@Service
@Primary
public class MemoryServiceImpl implements MemoryService {

	private Map<String, List<Integer>> slots;
	

	/**
	 * Constructor
	 */
	MemoryServiceImpl(){
		slots = new HashMap<>();
	}
	
	@Override
	public ResponseOperation create(String sessionId) {

		// Create sessionId stack
		slots.put(sessionId, new ArrayList<>());
		
		

		// Success response
		return ResponseOperation.builder().status(true).message(Messages.MEMORY_SESSION_CREATED).sessionId(sessionId)
				.build();
	}

	@Override
	public ResponseOperation add(String sessionId, Integer number) {

		// Get Queue from session ID
		List<Integer> sessionMemorySlot = getAndVerifySession(sessionId);

		// and push number
		sessionMemorySlot.add(number);

		// Success response
		return ResponseOperation.builder().status(true).message(Messages.MEMORY_SESSION_ADDED_NUMBER)
				.sessionId(sessionId).build();
	}

	@Override
	public List<Integer> getAll(String sessionId) throws InvalidOperationException {

		// Get Queue from session ID
		List<Integer> sessionMemorySlot = getAndVerifySession(sessionId);

		// Collect to list
		return sessionMemorySlot.stream().collect(Collectors.toList());
	}

	@Override
	public ResponseOperation calculate(String sessionId, String operation) {

		// Get Queue from session ID
		List<Integer> sessionMemory = getAndVerifySession(sessionId);

		Integer total = totalize(sessionMemory, operation);

		return ResponseOperation.builder().status(true).message("Totalized operation").sessionId(sessionId)
				.number(total).build();
	}

	@Override
	public ResponseOperation reset(String sessionId) {
		
		// Get Queue from session ID
		List<Integer> sessionMemory = getAndVerifySession(sessionId);

		// reset session If
		sessionMemory.clear();

		// Success response
		return ResponseOperation.builder().status(true).message(Messages.MEMORY_SESSION_RESET)
				.sessionId(sessionId)
				.build();
	}
	
	/**
	 * Totalize stack numbers.
	 * 
	 * @param sessionMemorySlot
	 * @param operation
	 * @return total
	 */
	private Integer totalize(List<Integer> sessionMemorySlot, String operation) {
		Integer total = 0;

		// Pop each number and calculate depending operation
		switch (operation) {
		case "+":
			total = sessionMemorySlot.stream().reduce(0, Math::addExact);
			break;

		case "-":
			if (Objects.nonNull(sessionMemorySlot) && !sessionMemorySlot.isEmpty()) {
				total = sessionMemorySlot.get(0);
				sessionMemorySlot.remove(0);
				for (Integer i : sessionMemorySlot) {
					total -= i;
				}
			}
			break;

		case "*":
			total = sessionMemorySlot.stream().reduce(1, Math::multiplyExact);
			break;

		case "^":
			if (Objects.nonNull(sessionMemorySlot) && !sessionMemorySlot.isEmpty()) {
				total = sessionMemorySlot.get(0);
				sessionMemorySlot.remove(0);
				for (Integer i : sessionMemorySlot) {
					total = Double.valueOf(Math.pow(total, i)).intValue();
				}
			}
			break;

		default:
			break;
		}

		return total;
	}

	/**
	 * DRY - General Method to verify sessionId
	 * 
	 * @param sessionId Session identifier
	 * @return session memory related to sessionId
	 * @throws InvalidOperationException When there is no sessionId on memory
	 */
	private List<Integer> getAndVerifySession(String sessionId) throws InvalidOperationException {
		List<Integer> sessionMemory = slots.get(sessionId);
		if (Objects.isNull(sessionMemory)) {
			throw new InvalidOperationException(Messages.MEMORY_NO_SLOT_WITH_SESSION_ID); // $NON-NLS-1$
		}

		return sessionMemory;
	}

}
