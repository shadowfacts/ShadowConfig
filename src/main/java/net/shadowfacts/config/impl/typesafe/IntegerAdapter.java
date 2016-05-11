package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class IntegerAdapter implements ConfigTypeAdapter<Config, Integer> {

	public static final IntegerAdapter instance = new IntegerAdapter();

	private IntegerAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Integer value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Integer readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			return config.getInt(category + "." + name);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
