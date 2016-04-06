package primeserver.generator;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of {@link Generator} that uses the Sieve of Sundaram.
 * 
 * @author sam
 */
class SieveOfSundaramGenerator extends BaseSieveGenerator {
	/**
	 * Initialises a new instance of SieveOfSundaramGenerator.
	 */
	protected SieveOfSundaramGenerator() {
		super("SieveOfSundaram");
	}

	/**
	 * @see primeserver.generator.Generator#generate(int)
	 */
	public SortedSet<Integer> generate(int max) {
		validate(max);

		// deal with the empty set scenario
		if (max < 2) {
			return new TreeSet<Integer>();
		}

		List<Boolean> sieve = createSieve((max - 1) / 2);

		// set all elements where (2i + 1)(2j + 1) to true
		for (int i = 1; i < sieve.size(); i++) {
			for (long j = i; j < sieve.size() - i + (2 * i + 1); j++) {
				long num = i + j + 2 * i * j;

				if (num <= sieve.size()) {
					sieve.set((int) num - 1, false);
				} else {
					break;
				}
			}
		}

		// extract the primes from the sieve (2 will be missing)
		SortedSet<Integer> primes = new TreeSet<Integer>();
		primes.add(2);
		for (int i = 0; i < sieve.size(); i++) {
			boolean isPrime = sieve.get(i);

			if (isPrime) {
				primes.add((i + 1) * 2 + 1);
			}
		}

		return primes;
	}
}
