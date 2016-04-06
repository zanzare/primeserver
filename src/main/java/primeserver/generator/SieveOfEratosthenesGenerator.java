package primeserver.generator;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of {@link Generator} that uses the Sieve of Eratosthenes.
 * 
 * @author sam
 */
class SieveOfEratosthenesGenerator extends BaseSieveGenerator {
	/**
	 * Initialises a new instance of SieveOfEratosthenesGenerator.
	 */
	protected SieveOfEratosthenesGenerator() {
		super("SieveOfEratosthenes");
	}

	/**
	 * @see primeserver.generator.Generator#generate(int)
	 */
	public SortedSet<Integer> generate(int max) {
		validate(max);

		// deal with blank list
		if (max < 2) {
			return new TreeSet<Integer>();
		}

		List<Boolean> sieve = createSieve(max - 1);

		// set all prime multiples to false
		for (int i = 0; i < sieve.size() / 2; i++) {
			boolean isPrime = sieve.get(i);

			if (isPrime) {
				int num = i + 2;

				for (int j = 2; num * j <= max; j++) {
					sieve.set(num * j - 2, false);
				}
			}
		}

		// extract the primes from the sieve
		SortedSet<Integer> primes = new TreeSet<Integer>();
		for (int i = 0; i < sieve.size(); i++) {
			Boolean isPrime = sieve.get(i);

			if (isPrime) {
				primes.add(i + 2);
			}
		}

		return primes;
	}
}
