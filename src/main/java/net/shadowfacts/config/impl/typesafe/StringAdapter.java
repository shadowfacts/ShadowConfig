package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class StringAdapter implements ConfigTypeAdapter<Config, String> {

	public static final StringAdapter instance = new StringAdapter();

	private StringAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, String value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public String readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			return config.getString(category + "." + name);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
