package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class NoDepartmentDataError extends DemoApplicatonServiceException {

	private static final long serialVersionUID = -7369331827724582972L;

	public NoDepartmentDataError() {
		super();
	}

	public NoDepartmentDataError(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoDepartmentDataError(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDepartmentDataError(String message) {
		super(message);
	}

	public NoDepartmentDataError(Throwable cause) {
		super(cause);
	}

}
