package net.shadowfacts.config.impl.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BoxedDoubleArrayAdapter implements TypesafeTypeAdapter<Config, Double[]> {

	public static final BoxedDoubleArrayAdapter instance = new BoxedDoubleArrayAdapter();

	private BoxedDoubleArrayAdapter() {
	}

	public Double[] load(String path, String description, ConfigWrapper<Config> config, Double[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Double> list = config.get().getDoubleList(path);
			return list.toArray(new Double[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
