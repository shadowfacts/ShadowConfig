package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class LongArrayAdapter implements TypesafeTypeAdapter<Config, long[]> {

	public static final LongArrayAdapter instance = new LongArrayAdapter();

	private LongArrayAdapter() {
	}

	@Override
	public long[] load(String path, String description, ConfigWrapper<Config> config, long[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Long> list = config.get().getLongList(path);
			long[] array = new long[list.size()];
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
