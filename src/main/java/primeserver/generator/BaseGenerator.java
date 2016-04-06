package primeserver.generator;

import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Base implementation of {@link Generator}.
 * 
 * @author sam
 */
abstract class BaseGenerator implements Generator {
	/** The name of the algorithm implemented by this generator. */
	protected final String algorithmName;

	/**
	 * Initialises a new instance of BaseGenerator for the specified algorithm name.
	 * 
	 * @param algorithmName
	 *            the algorithm name to use
	 */
	protected BaseGenerator(String algorithmName) {
		this.algorithmName = notNull(algorithmName);
	}

	/**
	 * Validates a length.
	 * 
	 * @param length
	 *            the length to validate
	 * @throws IllegalArgumentException
	 *             if length is out of range
	 */
	protected void validate(int length) {
		inclusiveBetween(0, Integer.MAX_VALUE, length);
	}

	/**
	 * @see primeserver.generator.Generator#getAlgorithmName()
	 */
	@Override
	public String getAlgorithmName() {
		return algorithmName;
	}
}
