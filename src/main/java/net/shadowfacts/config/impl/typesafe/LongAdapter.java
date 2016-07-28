package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class LongAdapter implements TypesafeTypeAdapter<Config, Long> {

	public static final LongAdapter instance = new LongAdapter();

	private LongAdapter() {
	}

	@Override
	public Long load(String path, String description, ConfigWrapper<Config> config, Long value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getLong(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
