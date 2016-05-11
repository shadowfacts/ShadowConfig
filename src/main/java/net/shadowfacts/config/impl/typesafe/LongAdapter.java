package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class LongAdapter implements ConfigTypeAdapter<Config, Long> {

	public static final LongAdapter instance = new LongAdapter();

	private LongAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Long value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Long readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			return config.getLong(category + "." + name);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
