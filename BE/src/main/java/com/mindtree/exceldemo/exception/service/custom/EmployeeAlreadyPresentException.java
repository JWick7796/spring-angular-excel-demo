package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class EmployeeAlreadyPresentException extends DemoApplicatonServiceException {

	private static final long serialVersionUID = -5087625662776163383L;

	public EmployeeAlreadyPresentException() {
		super();
	}

	public EmployeeAlreadyPresentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmployeeAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeAlreadyPresentException(String message) {
		super(message);
	}

	public EmployeeAlreadyPresentException(Throwable cause) {
		super(cause);
	}

}
