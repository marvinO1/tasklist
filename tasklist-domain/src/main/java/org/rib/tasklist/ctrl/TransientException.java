package org.rib.tasklist.ctrl;

public class TransientException extends TasklistException {
	private static final long serialVersionUID = 1L;

	public TransientException(String message) {
		super(message);
	}
	
	public TransientException(String message, Throwable cause) {
		super(message, cause);
	}
}
