package primeserver.generator;

/**
 * A factory for instances of {@link Generator}.
 * 
 * @author sam
 */
public class GeneratorFactory {
	/**
	 * Creates and returns a generator that uses trial division.
	 * 
	 * @return the newly instantiated generator
	 */
	public Generator createTrialDivisionGenerator() {
		return new TrialDivisionGenerator();
	}

	/**
	 * Creates and returns a generator that uses the Sieve of Eratosthenes.
	 * 
	 * @return the newly instantiated generator
	 */
	public Generator createSieveOfEratosthenesGenerator() {
		return new SieveOfEratosthenesGenerator();
	}

	/**
	 * Creates and returns a generator that uses the Sieve of Sundaram.
	 * 
	 * @return the newly instantiated generator
	 */
	public Generator createSieveOfSundaramGenerator() {
		return new SieveOfSundaramGenerator();
	}
}
