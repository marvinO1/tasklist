package org.rib.tasklist.ctrl;

public abstract class TasklistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TasklistException(String message) {
		super(message);
	}
	
	public TasklistException(String message, Throwable cause) {
		super(message, cause);
	}

}
