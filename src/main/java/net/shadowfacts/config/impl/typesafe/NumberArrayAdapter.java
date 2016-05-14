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
public class NumberArrayAdapter implements TypesafeTypeAdapter<Config, Number[]> {

	public static final NumberArrayAdapter instance = new NumberArrayAdapter();

	private NumberArrayAdapter() {
	}

	@Override
	public Number[] load(String path, String description, ConfigWrapper<Config> config, Number[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Number> list = config.get().getNumberList(path);
			return list.toArray(new Number[list.size()]);
		} else {
			config.set(config.get().withValue(path, ConfigValueFactory.fromAnyRef(value)));
			return value;
		}
	}

}
