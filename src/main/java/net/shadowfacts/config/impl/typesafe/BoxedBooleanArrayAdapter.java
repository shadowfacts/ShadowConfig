package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BoxedBooleanArrayAdapter implements TypesafeTypeAdapter<Config, Boolean[]> {

	public static final BoxedBooleanArrayAdapter instance = new BoxedBooleanArrayAdapter();

	private BoxedBooleanArrayAdapter() {
	}

	@Override
	public Boolean[] load(String path, String description, ConfigWrapper<Config> config, Boolean[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Boolean> list = config.get().getBooleanList(path);
			return list.toArray(new Boolean[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
