package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

/**
 * @author shadowfacts
 */
public class DoubleAdapter implements TypesafeTypeAdapter<Config, Double> {

	public static final DoubleAdapter instance = new DoubleAdapter();

	private DoubleAdapter() {
	}

	@Override
	public Double load(String path, String description, ConfigWrapper<Config> config, Double value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getDouble(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
