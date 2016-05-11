package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class NumberAdapter implements ConfigTypeAdapter<Config, Number> {

	public static final NumberAdapter instance = new NumberAdapter();

	private NumberAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Number value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Number readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			return config.getNumber(category + "." + name);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
