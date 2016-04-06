package primeserver.generator;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base generator for sieve-based generation.
 * 
 * @author sam
 */
abstract class BaseSieveGenerator extends BaseGenerator {
	/**
	 * Initialises a new instance of BaseSieveGenerator for the specified algorithm name.
	 * 
	 * @param algorithmName
	 *            the algorithm name to use
	 */
	protected BaseSieveGenerator(String algorithmName) {
		super(notNull(algorithmName));
	}

	/**
	 * Creates a sieve of the specified size, initialised to true.
	 * 
	 * @param size
	 *            the size of the sieve
	 * @return the newly created sieve
	 * @throws IllegalArgumentException
	 *             if size is negative
	 */
	protected List<Boolean> createSieve(int size) {
		return Stream.generate(() -> true).limit(size).collect(Collectors.toList());
	}
}
