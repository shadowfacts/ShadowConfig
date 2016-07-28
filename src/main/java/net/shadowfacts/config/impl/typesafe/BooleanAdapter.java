package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public class BooleanAdapter implements TypesafeTypeAdapter<Config, Boolean> {

	public static final BooleanAdapter instance = new BooleanAdapter();

	private BooleanAdapter() {
	}

	@Override
	public Boolean load(String path, String description, ConfigWrapper<Config> config, Boolean value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getBoolean(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value, description)));
			return value;
		}
	}

}
