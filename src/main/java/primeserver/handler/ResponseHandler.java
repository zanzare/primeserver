package primeserver.handler;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.HashMap;
import java.util.Map;

import primeserver.generator.Generator;

/**
 * A response handler.
 * 
 * @author sam
 */
public class ResponseHandler {
	/** The generator registry. */
	protected final GeneratorRegistry generatorRegistry;

	/**
	 * Initialises a new instance of ResponseHandler using the specified generator registry.
	 * 
	 * @param generatorRegistry
	 *            the generator registry to use
	 */
	public ResponseHandler(GeneratorRegistry generatorRegistry) {
		this.generatorRegistry = notNull(generatorRegistry);
	}

	/**
	 * Handles the specified request.
	 * 
	 * @param maxStr
	 *            the maximum number to generate for
	 * @param algorithm
	 *            the desired algorithm, which may be null (will cause the default)
	 * @return the response
	 */
	public Map<String, Object> handle(String maxStr, String algorithm) {
		try {
			int max = asInt(maxStr);

			Generator generator = generator(algorithm);
			Map<String, Object> response = new HashMap<>();

			long timer = System.nanoTime();

			// data
			response.put("data", generate(max, generator));

			// metadata
			response.put("time", System.nanoTime() - timer);
			response.put("status", "OK");
			response.put("algorithm", generator.getAlgorithmName());
			response.put("max", max);

			return response;
		} catch (Exception e) {
			// something went wrong: generate an error instead
			return generateError(e.getMessage());
		}
	}

	/**
	 * Returns the specified string as an integer.
	 * 
	 * @param maxStr
	 *            the string to use
	 * @return the string as an integer
	 * @throws IllegalArgumentException
	 *             if maxStr is null or not parseable
	 */
	protected int asInt(String maxStr) {
		if (maxStr == null) {
			throw new IllegalArgumentException("max not defined.");
		}

		try {
			return Integer.valueOf(maxStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("Cannot parse max [%s].", maxStr));
		}
	}

	/**
	 * Generates an error map with the specified message
	 * 
	 * @param message
	 *            the error message
	 * @return the error map
	 */
	protected Map<String, Object> generateError(String message) {
		notNull(message);

		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		response.put("message", message);
		return response;
	}

	/**
	 * Generates the primes for the specified max and generator.
	 * 
	 * @param max
	 *            the max number
	 * @param generator
	 *            the generator to use
	 * @return an array of primes, in order
	 */
	protected Integer[] generate(int max, Generator generator) {
		return generator.generate(max).toArray(new Integer[0]);
	}

	/**
	 * Gets a generator for the specified algorithm.
	 * 
	 * @param algorithm
	 *            the desired algorithm, which may be null (in which case the default is used)
	 * @return the generator
	 * @throws IllegalArgumentException
	 *             if algorithm does not exist
	 */
	protected Generator generator(String algorithm) {
		return generatorRegistry.getGenerator(algorithm);
	}
}
