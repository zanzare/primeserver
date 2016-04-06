package primeserver;

import static org.apache.commons.lang3.Validate.notNull;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Web application configuration.
 * 
 * <p>
 * Contains runtime configuration information as an alternative to using web.xml
 * </p>
 * 
 * @author sam
 */
public class WebApplicationConfiguration extends ResourceConfig {
	/**
	 * Initialises a new instance of WebApplicationConfiguration.
	 * 
	 * @param clazz
	 *            the underlying class
	 */
	public WebApplicationConfiguration(Class<?> clazz) {
		super(notNull(clazz));
		packages("com.jersey.jaxb;com.fasterxml.jackson.jaxrs.json");
	}
}
