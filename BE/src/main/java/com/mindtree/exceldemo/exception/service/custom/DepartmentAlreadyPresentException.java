package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class DepartmentAlreadyPresentException extends DemoApplicatonServiceException {

	private static final long serialVersionUID = -5087625662776163383L;

	public DepartmentAlreadyPresentException() {
		super();
	}

	public DepartmentAlreadyPresentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DepartmentAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepartmentAlreadyPresentException(String message) {
		super(message);
	}

	public DepartmentAlreadyPresentException(Throwable cause) {
		super(cause);
	}

}
