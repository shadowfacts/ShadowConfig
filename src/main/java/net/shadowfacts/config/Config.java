package net.shadowfacts.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a configuration class
 *
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {

	/**
	 * @deprecated To be removed in ShadowConfig for the next MC version
	 * @return The name of this configuration
	 */
	@Deprecated
	String name();

	/**
	 *  Marks a field as a configuration property
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface Prop {

		/**
		 * @return The category for this configuration property
		 */
		String category() default "general";

		/**
		 * @return The name of this configuration property. If left empty, the field name will be used.
		 */
		String name() default "";

		/**
		 * @return The description/comment for this configuration property
		 */
		String description() default "";

	}

}
