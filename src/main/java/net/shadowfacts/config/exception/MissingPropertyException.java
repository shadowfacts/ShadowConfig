package net.shadowfacts.config.exception;

/**
 * @author shadowfacts
 */
public class MissingPropertyException extends ConfigException {

	public MissingPropertyException() {
		super();
	}

	public MissingPropertyException(String message) {
		super(message);
	}

	public MissingPropertyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingPropertyException(Throwable cause) {
		super(cause);
	}
	
}
