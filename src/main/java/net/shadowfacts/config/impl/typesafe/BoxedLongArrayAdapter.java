package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BoxedLongArrayAdapter implements TypesafeTypeAdapter<Config, Long[]> {

	public static final BoxedLongArrayAdapter instance = new BoxedLongArrayAdapter();

	private BoxedLongArrayAdapter() {
	}

	@Override
	public Long[] load(String path, String description, ConfigWrapper<Config> config, Long[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Long> list = config.get().getLongList(path);
			return list.toArray(new Long[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
