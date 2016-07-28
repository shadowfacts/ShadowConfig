package net.shadowfacts.config;

import net.shadowfacts.config.exception.ConfigException;
import net.shadowfacts.config.impl.typesafe.TypesafeAdapter;
import net.shadowfacts.mirror.Mirror;
import net.shadowfacts.mirror.MirrorClass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages storing type adapters and loading configurations
 *
 * @author shadowfacts
 */
public class ConfigManager {

	private static Map<MirrorClass<?>, Map> configClassToTypeAdapterMap = new HashMap<>();

	static {
		TypesafeAdapter.init();
	}

	// INTERNAL
	private static boolean hasConfigClazz(Class<?> configClazz) {
		return configClassToTypeAdapterMap.containsKey(Mirror.of(configClazz));
	}

	@SuppressWarnings("unchecked")
	private static <C, V> Map<Class<V>, ConfigTypeAdapter<C, V>> getAdapterMap(Class<C> configClazz) {
		return (Map<Class<V>, ConfigTypeAdapter<C, V>>)configClassToTypeAdapterMap.get(Mirror.of(configClazz));
	}

	private static <C, V> ConfigTypeAdapter<C, V> getTypeAdapter(Class<C> configClazz, Class<V> valueClazz) {
		Map<Class<V>, ConfigTypeAdapter<C, V>> adapterMap = getAdapterMap(configClazz);
		if (adapterMap != null) {
			return adapterMap.get(Mirror.of(valueClazz));
		}
		return null;
	}

	// PUBLIC
	/**
	 * Loads a configuration
	 * @param clazz The class to load from
	 * @param configClazz The class of the configuration object to use
	 * @param config The configuration object to use
	 * @param <C> The type of the configuration object
	 * @return The modified configuration object. <b>This may be different from the object passed in or it may be the same instance</b>
	 * @throws ConfigException If there is a problem loading a property from the configuration or setting a field or if there are type adapter(s) missing
	 */
	public static <C> C load(Class<?> clazz, Class<C> configClazz, C config) throws ConfigException {
		MirrorClass<?> mirror = Mirror.of(clazz);

		if (hasConfigClazz(configClazz) && mirror.hasAnnotation(Config.class)) {
			ConfigWrapper<C> wrapper = ConfigWrapper.of(config);

			mirror.declaredFields()
					.isStatic()
					.hasAnnotation(Config.Prop.class)
					.forEach(f -> {
						Config.Prop prop = f.getAnnotation(Config.Prop.class);

						MirrorClass fieldClazz = f.type();

						@SuppressWarnings("unchecked")
						ConfigTypeAdapter<C, Object> adapter = getTypeAdapter(configClazz, fieldClazz.unwrap());

						if (adapter != null) {
							String name = prop.name().isEmpty() ? f.name() : prop.name();

							try {
								f.setAccessible(true);
								f.set(null, adapter.load(prop.category(), name, prop.description(), wrapper, f.get(null)));
							} catch (Exception e) {
								System.err.println("Problem loading field " + f.name() + " for config class " + mirror.fullName());
								throw new ConfigException(e);
							}
						} else {
							throw new ConfigException("No type adapter for configuration class " + configClazz.getName() + " and field type " + fieldClazz.fullName());
						}
					});

			return wrapper.get();
		} else {
			throw new ConfigException("No configuration handlers for configuration class " + configClazz.getName());
		}
	}

	/**
	 * Registers a type adapter
	 * @param configClazz The configuration class that this type adapter is suitable for
	 * @param fieldClass The type that this type adapter accepts
	 * @param adapter The adapter instance
	 * @param <C> The type of the configuration class
	 * @param <V> The type of the type adapter
	 */
	@SuppressWarnings("unchecked")
	public static <C, V> void registerTypeAdapter(Class<C> configClazz, Class<V> fieldClass, ConfigTypeAdapter<C, V> adapter) {
		if (!hasConfigClazz(configClazz)) {
			configClassToTypeAdapterMap.put(Mirror.of(configClazz), new HashMap<>());
		}

		configClassToTypeAdapterMap.get(Mirror.of(configClazz)).put(Mirror.of(fieldClass), adapter);
	}

}
