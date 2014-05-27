package org.rib.tasklist.ctrl;

public class TasklistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ExceptionType exceptionType;
	
	public TasklistException(String message) {
        this(ExceptionType.PERSISTENT, message);
	}
		
	public TasklistException(ExceptionType type, String message) {
		super(message);
		this.exceptionType = type;
	}
	
	public TasklistException(String message, Throwable cause) {
		this(ExceptionType.PERSISTENT, message, cause);
		
	}

	
	public TasklistException(ExceptionType type, String message, Throwable cause) {
		super(message, cause);
		this.exceptionType = type;
	}
	
	public ExceptionType getExceptionType() {
		return this.exceptionType;
	}
}
