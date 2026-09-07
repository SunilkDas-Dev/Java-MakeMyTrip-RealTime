package com.pro.exceptions;

public class SeatsUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeatsUnavailableException(String msg) {
		super(msg);
	}

}
