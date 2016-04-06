package primeserver.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of {@link Generator} that uses trial division.
 * 
 * @author sam
 */
class TrialDivisionGenerator extends BaseGenerator {
	/**
	 * Initialises a new instance of TrialDivisionGenerator.
	 */
	protected TrialDivisionGenerator() {
		super("TrialDivision");
	}

	/**
	 * @see primeserver.generator.Generator#generate(int)
	 */
	public SortedSet<Integer> generate(int max) {
		validate(max);

		// add all primes to the list of primes
		List<Integer> primes = new ArrayList<>();
		for (int index = 2; index <= max; index++) {
			if (isPrime(index, primes)) {
				primes.add(index);
			}
		}

		// return the primes as a set
		return new TreeSet<Integer>(primes);
	}

	/**
	 * Determines whether the specified number is prime.
	 * 
	 * @param num
	 *            the number to evaluate, which must be &gt; 1
	 * @param primes
	 *            the set of all primes &lt; num
	 * @return true if it's prime, false otherwise
	 */
	protected boolean isPrime(int num, List<Integer> primes) {
		for (int prime : primes) {
			if (num % prime == 0) {
				// found a factor
				return false;
			}
			if (num / prime == 2) {
				// we have reached num / 2 so there can be no more primes
				return true;
			}
		}

		return true;
	}
}
