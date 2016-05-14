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
public class StringAdapter implements TypesafeTypeAdapter<Config, String> {

	public static final StringAdapter instance = new StringAdapter();

	private StringAdapter() {
	}

	@Override
	public String load(String path, String description, ConfigWrapper<Config> config, String value) throws ConfigException {
		if (config.get().hasPath(path)) {
			return config.get().getString(path);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
