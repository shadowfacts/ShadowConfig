package net.shadowfacts.config;

import net.shadowfacts.config.exception.ConfigException;

/**
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
	 * @param value The value to save
	 * @return The new configuration object
	 * @throws ConfigException
	 */
	C writeToConfig(String category, String name, String description, C config, V value) throws ConfigException;

	/**
	 * Reads a value from the config
	 * @param category The property category
	 * @param name The property name
	 * @param config The configuration object
	 * @return The value read from the config
	 * @throws ConfigException
	 */
	V readFromConfig(String category, String name, C config) throws ConfigException;

}
