package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class StringArrayAdapter implements TypesafeTypeAdapter<Config, String[]> {

	public static final StringArrayAdapter instance = new StringArrayAdapter();

	private StringArrayAdapter() {
	}

	@Override
	public String[] load(String path, String description, ConfigWrapper<Config> config, String[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<String> list = config.get().getStringList(path);
			return list.toArray(new String[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
