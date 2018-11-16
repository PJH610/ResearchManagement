package com.zhuoyue.researchManement.exception;

public class BadRequestException extends RuntimeException {

	private int code = 400;

	public BadRequestException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BadRequestException(String message) {
		super(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}