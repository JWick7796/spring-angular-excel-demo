package com.mindtree.exceldemo.exception.service.custom;

import com.mindtree.exceldemo.exception.service.DemoApplicatonServiceException;

public class FileReadException extends DemoApplicatonServiceException {

	private static final long serialVersionUID = -8418926006758125474L;

	public FileReadException() {
		super();
	}

	public FileReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileReadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileReadException(String message) {
		super(message);
	}

	public FileReadException(Throwable cause) {
		super(cause);
	}

}
