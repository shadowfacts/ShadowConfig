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
public class BoxedDoubleArrayAdapter implements ConfigTypeAdapter<Config, Double[]> {

	public static final BoxedDoubleArrayAdapter instance = new BoxedDoubleArrayAdapter();

	private BoxedDoubleArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, Double[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public Double[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<Double> list = config.getDoubleList(category + "." + name);
			return list.toArray(new Double[list.size()]);
		} catch(com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
