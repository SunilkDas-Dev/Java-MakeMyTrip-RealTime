package com.pro.exceptions;

public class CancelBookingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CancelBookingException(String message) {
		super(message);
	}

}