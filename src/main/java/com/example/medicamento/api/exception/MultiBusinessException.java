package com.example.medicamento.api.exception;

import java.util.ArrayList;
import java.util.List;

public class MultiBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<BusinessException> exceptions = new ArrayList<>();

	public MultiBusinessException() {
	}

	public List<BusinessException> getExceptions() {
		return exceptions;
	}

	public void addException(BusinessException businessException) {
		this.exceptions.add(businessException);
	}

	@Override
	public String toString() {
		return "MultiBusinessException [exceptions=" + exceptions + "]";
	}
	
}
