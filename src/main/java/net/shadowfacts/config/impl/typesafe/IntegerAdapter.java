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
public class IntegerAdapter implements TypesafeTypeAdapter<Config, Integer> {

	public static final IntegerAdapter instance = new IntegerAdapter();

	private IntegerAdapter() {
	}

	@Override
	public Integer load(String path, String description, ConfigWrapper<Config> config, Integer value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getInt(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
