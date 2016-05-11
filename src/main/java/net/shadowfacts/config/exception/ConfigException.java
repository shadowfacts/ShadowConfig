package net.shadowfacts.config.exception;

/**
 * @author shadowfacts
 */
public class ConfigException extends RuntimeException {

	public ConfigException() {
		super();
	}

	public ConfigException(String message) {
		super(message);
	}

	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigException(Throwable cause) {
		super(cause);
	}

}
