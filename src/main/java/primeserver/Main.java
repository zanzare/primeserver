package primeserver;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main entry point.
 * 
 * @author sam
 */
public class Main {
	private static final Logger log = Logger.getLogger(Main.class.getName());

	/** Bind address. */
	private static final String BIND_ADDRESS = "http://localhost/";

	/** Bind port. */
	private static final int BIND_PORT = 1080;

	/**
	 * Main entry point.
	 * 
	 * @param args
	 *            program arguments
	 */
	public static void main(String[] args) {
		// initialise grizzly
		try {
			URI baseUri = UriBuilder.fromUri(BIND_ADDRESS).port(BIND_PORT).build();
			ResourceConfig config = new WebApplicationConfiguration(Primes.class);
			GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Cannot initialise.", e);
			System.err.println("Cannot initialise: " + e.getMessage());
			System.exit(-1);
		}

		log.info("Application started.");
	}
}
