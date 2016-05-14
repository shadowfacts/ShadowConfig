package net.shadowfacts.config;

/**
 * @author shadowfacts
 */
public final class ConfigWrapper<C> {

	private C config;

	private ConfigWrapper(C config) {
		this.config = config;
	}

	public C get() {
		return config;
	}

	public void set(C config) {
		this.config = config;
	}

	public static <C> ConfigWrapper<C> of(C config) {
		return new ConfigWrapper<>(config);
	}

}
