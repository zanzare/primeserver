package primeserver;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import primeserver.generator.GeneratorFactory;
import primeserver.handler.GeneratorRegistry;
import primeserver.handler.ResponseHandler;

/**
 * Primes resource.
 * 
 * <p>
 * This class acts as an intermediary between the web layer and a {@link ResponseHandler}. Results are returned as a
 * JSON stream.
 * </p>
 */
@Path("primes")
public class Primes extends ResourceConfig {
	/** Response handler. */
	protected final ResponseHandler rh = createResponseHandler();

	/**
	 * Initialises a new instance of Primes.
	 */
	public Primes() {
		packages("com.jersey.jaxb;com.fasterxml.jackson.jaxrs.json");
	}

	/**
	 * Method handling HTTP GET requests.
	 * 
	 * @param max
	 *            the maximum number to consider
	 * @param algorithm
	 *            the desired algorithm, or null if the default should be used
	 * @return the desired primes
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> process(@QueryParam("max") String max, @QueryParam("algorithm") String algorithm) {
		return rh.handle(max, algorithm);
	}

	/**
	 * Creates a response handler for use with this resource.
	 * 
	 * @return the newly created response handler
	 */
	protected static ResponseHandler createResponseHandler() {
		return new ResponseHandler(new GeneratorRegistry(new GeneratorFactory()));
	}
}
