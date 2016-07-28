package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class NumberAdapter implements TypesafeTypeAdapter<Config, Number> {

	public static final NumberAdapter instance = new NumberAdapter();

	private NumberAdapter() {
	}

	@Override
	public Number load(String path, String description, ConfigWrapper<Config> config, Number value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getNumber(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
