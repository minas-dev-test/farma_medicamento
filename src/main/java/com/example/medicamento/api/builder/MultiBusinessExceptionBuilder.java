package com.example.medicamento.api.builder;

import org.apache.commons.lang3.StringUtils;

import com.example.medicamento.api.exception.BusinessException;
import com.example.medicamento.api.exception.MultiBusinessException;

public class MultiBusinessExceptionBuilder {
	private MultiBusinessException multiBusinessException;

	private MultiBusinessExceptionBuilder() {
	}

	public static MultiBusinessExceptionBuilder instance() {
		return new MultiBusinessExceptionBuilder();
	}

	public MultiBusinessExceptionBuilder addException(String msg) {
		BusinessException businessException = new BusinessException(msg);
		return addException(businessException);
	}

	public MultiBusinessExceptionBuilder addException(String msg, boolean condition) {
		if (condition) {
			BusinessException businessException = new BusinessException(msg);
			return addException(businessException);
		} else {
			return this;
		}
	}

	public MultiBusinessExceptionBuilder addRequiredException(String fieldName, Object fieldValue) {
		String mensagem = String.format("O campo '%s' é obrigatório.", fieldName);
		return addException(mensagem, fieldValue == null || !StringUtils.isNotBlank(fieldName));
	}
	
	public MultiBusinessExceptionBuilder addException(Throwable t) {
		BusinessException businessException = new BusinessException(t);
		return addException(businessException);
	}

	public MultiBusinessExceptionBuilder addException(String msg, Throwable t) {
		BusinessException businessException = new BusinessException(msg);
		return addException(businessException);
	}

	private MultiBusinessExceptionBuilder addException(BusinessException businessException) {
		if (multiBusinessException == null) {
			multiBusinessException = new MultiBusinessException();
		}

		this.multiBusinessException.addException(businessException);
		return this;
	}

	public void handleThrowException() {
		if (this.multiBusinessException != null) {
			throw this.multiBusinessException;
		}
	}
}