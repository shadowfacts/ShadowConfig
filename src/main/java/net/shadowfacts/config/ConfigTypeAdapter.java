package net.shadowfacts.config;

import net.shadowfacts.config.exception.ConfigException;

/**
 * An adapter for loading properties from configurations
 *
 * @author shadowfacts
 *
 * @param <C> The Configuration type
 * @param <V> The Value type
 */
public interface ConfigTypeAdapter<C, V> {

	/**
	 * Saves a value to the config
	 * @param category The property category
	 * @param name The property name
	 * @param description The property description/comment
	 * @param config The configuration object
	 * @param value The value to use if the config object doesn't contain the value
	 * @return The result
	 * @throws ConfigException If there's a problem loading the configuration property
	 */
	V load(String category, String name, String description, ConfigWrapper<C> config, V value) throws ConfigException;

}
