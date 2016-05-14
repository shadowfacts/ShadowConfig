package net.shadowfacts.config;

import static org.junit.Assert.*;

import com.typesafe.config.ConfigValueFactory;
import org.junit.Test;
import com.typesafe.config.ConfigFactory;

/**
 * @author shadowfacts
 */
public class ConfigTest {

	@Test
	public void testLoadDefaultsPublic() {
		com.typesafe.config.Config config = ConfigManager.load(Test1.class, com.typesafe.config.Config.class, ConfigFactory.empty());

		assertEquals(Test1.test, config.getString("general.test"));
		assertEquals(Test1.i, config.getInt("ints.i"));
	}

	@Test
	public void testLoadCustomPublic() {
		com.typesafe.config.Config config = ConfigFactory.empty();
		config = config.withValue("general.test", ConfigValueFactory.fromAnyRef("world"));
		config = config.withValue("ints.i", ConfigValueFactory.fromAnyRef(42));

		ConfigManager.load(Test1.class, com.typesafe.config.Config.class, config);

		assertEquals(Test1.test, config.getString("general.test"));
		assertEquals(Test1.i, config.getInt("ints.i"));
	}

	@Test
	public void testLoadDefaultsPrivate() {
		com.typesafe.config.Config config = ConfigManager.load(Test2.class, com.typesafe.config.Config.class, ConfigFactory.empty());

		assertEquals(Test2.test, config.getString("general.test"));
	}

	@Test
	public void testLoadCustomPrivate() {
		com.typesafe.config.Config config = ConfigFactory.empty();
		config = config.withValue("general.test", ConfigValueFactory.fromAnyRef("world"));

		ConfigManager.load(Test2.class, com.typesafe.config.Config.class, config);

		assertEquals(Test2.test, config.getString("general.test"));
	}

	@Config(name = "test")
	public static class Test1 {

		@Config.Prop
		public static String test = "hello";

		@Config.Prop(category = "ints")
		public static int i = 3;

	}

	@Config(name = "test2")
	public static class Test2 {

		@Config.Prop
		private static String test = "hello";

	}

}
