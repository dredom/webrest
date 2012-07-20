package com.lvl.au.exception;

public class BadParameterException extends AppException {

	private static final long serialVersionUID = 1L;

	public BadParameterException() {
		super();
	}

	public BadParameterException(String arg0) {
		super(arg0);
	}

	public BadParameterException(Throwable arg0) {
		super(arg0);
	}

	public BadParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
