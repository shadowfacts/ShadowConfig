package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class StringArrayAdapter implements ConfigTypeAdapter<Config, String[]> {

	public static final StringArrayAdapter instance = new StringArrayAdapter();

	private StringArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, String[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public String[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<String> list = config.getStringList(category + "." + name);
			return list.toArray(new String[list.size()]);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
