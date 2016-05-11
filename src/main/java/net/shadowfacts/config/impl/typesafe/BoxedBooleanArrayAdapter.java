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
public class BoxedBooleanArrayAdapter implements ConfigTypeAdapter<Config, Boolean[]> {

	public static final BoxedBooleanArrayAdapter instance = new BoxedBooleanArrayAdapter();

	private BoxedBooleanArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Boolean[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Boolean[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<Boolean> list = config.getBooleanList(category + "." + name);
			return list.toArray(new Boolean[list.size()]);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
