package primeserver.generator;

public class SieveOfEratosthenesGeneratorTest extends BaseGeneratorTest {
	/**
	 * @see primeserver.generator.BaseGeneratorTest#createGenerator()
	 */
	@Override
	protected Generator createGenerator() {
		return new SieveOfEratosthenesGenerator();
	}
}
