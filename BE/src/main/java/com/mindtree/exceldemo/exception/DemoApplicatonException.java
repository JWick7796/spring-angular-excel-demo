package com.mindtree.exceldemo.exception;

public class DemoApplicatonException extends Error {

	private static final long serialVersionUID = 2586365382437246501L;

	public DemoApplicatonException() {
	}

	public DemoApplicatonException(String message) {
		super(message);
	}

	public DemoApplicatonException(Throwable cause) {
		super(cause);
	}

	public DemoApplicatonException(String message, Throwable cause) {
		super(message, cause);
	}

	public DemoApplicatonException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
