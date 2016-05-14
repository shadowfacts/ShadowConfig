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
public class BooleanArrayAdapter implements TypesafeTypeAdapter<Config, boolean[]> {

	public static final BooleanArrayAdapter instance = new BooleanArrayAdapter();

	private BooleanArrayAdapter() {
	}

	@Override
	public boolean[] load(String path, String description, ConfigWrapper<Config> config, boolean[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Boolean> list = config.get().getBooleanList(path);
			boolean[] array = new boolean[list.size()];
			for (int i = 0; i < list.size(); i++) {
				array[i] = list.get(i);
			}
			return array;
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
