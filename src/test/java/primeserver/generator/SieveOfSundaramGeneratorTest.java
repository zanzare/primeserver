package primeserver.generator;

public class SieveOfSundaramGeneratorTest extends BaseGeneratorTest {
	/**
	 * @see primeserver.generator.BaseGeneratorTest#createGenerator()
	 */
	@Override
	protected Generator createGenerator() {
		return new SieveOfSundaramGenerator();
	}
}
