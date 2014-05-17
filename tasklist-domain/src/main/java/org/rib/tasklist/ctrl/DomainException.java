package org.rib.tasklist.ctrl;

public class DomainException extends TasklistException {
	private static final long serialVersionUID = 1L;

	public DomainException(String message) {
		super(message);
	}
	
	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
