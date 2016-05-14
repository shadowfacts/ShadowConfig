package net.shadowfacts.config.impl.typesafe;

import net.shadowfacts.config.ConfigTypeAdapter;
import net.shadowfacts.config.ConfigWrapper;
import net.shadowfacts.config.exception.ConfigException;

/**
 * @author shadowfacts
 */
public interface TypesafeTypeAdapter<C, V> extends ConfigTypeAdapter<C, V> {

	@Override
	default V load(String category, String name, String description, ConfigWrapper<C> config, V value) throws ConfigException {
		return load(category + "." + name, description, config, value);
	}

	V load(String path, String description, ConfigWrapper<C> config, V value) throws ConfigException;

}
