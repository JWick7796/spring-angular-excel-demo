package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class DepartmentNotPresentException extends DemoApplicatonServiceException {

	private static final long serialVersionUID = 8338278706149122010L;

	public DepartmentNotPresentException() {
	}

	public DepartmentNotPresentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DepartmentNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepartmentNotPresentException(String message) {
		super(message);
	}

	public DepartmentNotPresentException(Throwable cause) {
		super(cause);
	}

}
