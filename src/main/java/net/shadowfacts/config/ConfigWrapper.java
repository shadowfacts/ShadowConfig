package net.shadowfacts.config;

/**
 * A wrapper class that allows for the configuration instance to be replaced by {@link ConfigTypeAdapter}s
 *
 * @author shadowfacts
 *
 * @param <C> The type of the configuration
 */
public final class ConfigWrapper<C> {

	private C config;

	private ConfigWrapper(C config) {
		this.config = config;
	}

	/**
	 * @return The current configuration instance
	 */
	public C get() {
		return config;
	}

	/**
	 * Replaces the current configuration instance with the new one
	 * @param config The new configuration instance
	 */
	public void set(C config) {
		this.config = config;
	}

	static <C> ConfigWrapper<C> of(C config) {
		return new ConfigWrapper<>(config);
	}

}
