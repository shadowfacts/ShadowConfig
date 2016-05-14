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
public class IntegerArrayAdapter implements TypesafeTypeAdapter<Config, int[]> {

	public static final IntegerArrayAdapter instance = new IntegerArrayAdapter();

	private IntegerArrayAdapter() {
	}

	@Override
	public int[] load(String path, String description, ConfigWrapper<Config> config, int[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Integer> list = config.get().getIntList(path);
			int[] array = new int[list.size()];
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
