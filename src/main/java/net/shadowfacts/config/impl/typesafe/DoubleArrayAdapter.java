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
public class DoubleArrayAdapter implements TypesafeTypeAdapter<Config, double[]> {

	public static final DoubleArrayAdapter instance = new DoubleArrayAdapter();

	private DoubleArrayAdapter() {
	}

	@Override
	public double[] load(String path, String description, ConfigWrapper<Config> config, double[] value) throws ConfigException {
		if (config.get().hasPath(path)) {
			List<Double> list = config.get().getDoubleList(path);
			double[] array = new double[list.size()];
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
