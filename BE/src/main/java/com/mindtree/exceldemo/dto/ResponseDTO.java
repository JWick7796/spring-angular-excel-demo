package com.mindtree.exceldemo.dto;

public class ResponseDTO<T> {

	private T data;
	private ErrorDTO error;
	private String message;
	private boolean success;

	public ResponseDTO() {
	}

	public ResponseDTO(T data, ErrorDTO error, String message, boolean success) {
		super();
		this.data = data;
		this.error = error;
		this.message = message;
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
