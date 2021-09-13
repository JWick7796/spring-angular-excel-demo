package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class EmployeeNotPresentException extends DemoApplicatonServiceException {

	private static final long serialVersionUID = 8338278706149122010L;

	public EmployeeNotPresentException() {
	}

	public EmployeeNotPresentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmployeeNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotPresentException(String message) {
		super(message);
	}

	public EmployeeNotPresentException(Throwable cause) {
		super(cause);
	}

}
