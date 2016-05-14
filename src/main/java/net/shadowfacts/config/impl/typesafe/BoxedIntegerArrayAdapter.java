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
public class BoxedIntegerArrayAdapter implements TypesafeTypeAdapter<Config, Integer[]> {

	public static final BoxedIntegerArrayAdapter instance = new BoxedIntegerArrayAdapter();

	private BoxedIntegerArrayAdapter() {
	}

	@Override
	public Integer[] load(String path, String description, ConfigWrapper<Config> config, Integer[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Integer> list = config.get().getIntList(path);
			return list.toArray(new Integer[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
