package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class DoubleAdapter implements ConfigTypeAdapter<Config, Double> {

	public static final DoubleAdapter instance = new DoubleAdapter();

	private DoubleAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Double value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Double readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			return config.getDouble(category + "." + name);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
