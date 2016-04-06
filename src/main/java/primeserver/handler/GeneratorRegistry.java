package primeserver.handler;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.HashMap;
import java.util.Map;

import primeserver.generator.Generator;
import primeserver.generator.GeneratorFactory;

/**
 * A generator registry.
 * 
 * <p>
 * Initialises a series of generators using the {@link GeneratorFactory} specified in the ctor, and provides access to
 * these, in addition to specifying a default generator.
 * </p>
 * 
 * @author sam
 */
public class GeneratorRegistry {
	/** Default algorithm. */
	protected static final String DEFAULT_ALGORITHM = "sieveofsundaram";

	/** The underlying generators. */
	protected final Map<String, Generator> generators;

	/**
	 * Initialises a new instance of GeneratorRegistry using the specified generator factory.
	 * 
	 * @param gf
	 *            the generator factory
	 */
	public GeneratorRegistry(GeneratorFactory gf) {
		generators = createGenerators(notNull(gf));
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
	public Generator getGenerator(String algorithm) {
		if (algorithm == null) {
			return generators.get(DEFAULT_ALGORITHM);
		}

		// get the desired generator
		Generator generator = generators.get(algorithm.toLowerCase());

		// postcondition
		if (generator != null) {
			return generator;
		} else {
			throw new IllegalArgumentException(String.format("Unknown algorithm: [%s]", algorithm));
		}
	}

	/**
	 * Registers the specified generator.
	 * 
	 * @param generator
	 *            the generator to register
	 * @param generators
	 *            the map of generators
	 */
	protected static void register(Generator generator, Map<String, Generator> generators) {
		generators.put(generator.getAlgorithmName().toLowerCase(), notNull(generator));
	}

	/**
	 * Creates the underlying prime number generators.
	 * 
	 * @param gf
	 *            a generator factory for generator creation
	 * @return a map of (generatorName -&gt; generator) for each generator, newly created
	 */
	protected static Map<String, Generator> createGenerators(GeneratorFactory gf) {
		notNull(gf);

		Map<String, Generator> generators = new HashMap<>();
		register(gf.createTrialDivisionGenerator(), generators);
		register(gf.createSieveOfEratosthenesGenerator(), generators);
		register(gf.createSieveOfSundaramGenerator(), generators);

		return generators;
	}
}
