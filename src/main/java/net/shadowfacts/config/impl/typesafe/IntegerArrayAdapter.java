package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.exception.MissingPropertyException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class IntegerArrayAdapter implements ConfigTypeAdapter<Config, int[]> {

	public static final IntegerArrayAdapter instance = new IntegerArrayAdapter();

	private IntegerArrayAdapter() {
	}

	@Override
	public Config writeToConfig(String category, String name, String description, Config config, int[] value) throws ConfigException {
		return config.withValue(category + "." + name, ConfigValueFactory.fromAnyRef(value, description));
	}

	@Override
	public int[] readFromConfig(String category, String name, Config config) throws ConfigException {
		try {
			List<Integer> list = config.getIntList(category + "." + name);
			int[] array = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				array[i] = list.get(i);
			}
			return array;
		} catch (com.typesafe.config.ConfigException.Missing e) {
			throw new MissingPropertyException(String.format("Missing property %s.%s", category, name), e);
		}
	}

}
