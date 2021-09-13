package com.mindtree.exceldemo.exception.service;

import com.mindtree.exceldemo.exception.DemoApplicatonException;

public class DemoApplicatonServiceException extends DemoApplicatonException {

	private static final long serialVersionUID = 1117225211735591750L;

	public DemoApplicatonServiceException() {
		super();
	}

	public DemoApplicatonServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DemoApplicatonServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DemoApplicatonServiceException(String message) {
		super(message);
	}

	public DemoApplicatonServiceException(Throwable cause) {
		super(cause);
	}

}
