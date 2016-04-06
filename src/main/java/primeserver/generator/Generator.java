package primeserver.generator;

import java.util.SortedSet;

/**
 * A prime number generator.
 * 
 * <p>
 * Implementations must be thread safe.
 * </p>
 * 
 * @author sam
 */
public interface Generator {
	/**
	 * Generates a sorted set of prime numbers.
	 * 
	 * @param max
	 *            the maximum prime number required
	 * @return the generated primes, sorted
	 */
	public SortedSet<Integer> generate(int max);

	/**
	 * Gets the name of the algorithm implemented by this generator.
	 * 
	 * @return the name of the algorithm
	 */
	public String getAlgorithmName();
}
