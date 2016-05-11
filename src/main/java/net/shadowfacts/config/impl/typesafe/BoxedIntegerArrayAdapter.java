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
public class BoxedIntegerArrayAdapter implements ConfigTypeAdapter<Config, Integer[]> {

	public static final BoxedIntegerArrayAdapter instance = new BoxedIntegerArrayAdapter();

	private BoxedIntegerArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Integer[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Integer[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<Integer> list = config.getIntList(category + "." + name);
			return list.toArray(new Integer[list.size()]);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
