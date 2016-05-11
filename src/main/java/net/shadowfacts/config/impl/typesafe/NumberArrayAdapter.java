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
public class NumberArrayAdapter implements ConfigTypeAdapter<Config, Number[]> {

	public static final NumberArrayAdapter instance = new NumberArrayAdapter();

	private NumberArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Number[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Number[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<Number> list = config.getNumberList(category + "." + name);
			return list.toArray(new Number[list.size()]);
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
