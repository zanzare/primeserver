package primeserver.generator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class BaseGeneratorTest {
	/** Test target. */
	protected Generator generator;

	@Before
	public void setUpGenerator() {
		generator = createGenerator();
	}

	@Test
	public void emptyListCanBeGenerated() {
		assertTrue(generator.generate(0).isEmpty());
	}

	@Test
	public void singletonListCanBeGenerated() {
		Integer[] expected = new Integer[] { 2 };
		assertArrayEquals(expected, generator.generate(2).toArray(new Integer[0]));
	}

	@Test
	public void primesUpToOneHundredAreCorrect() {
		Integer[] expected = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
				73, 79, 83, 89, 97 };
		assertArrayEquals(expected, generator.generate(100).toArray(new Integer[0]));
	}

	@Test
	public void tenThousandPrimesCanBeGenerated() {
		assertEquals(10000, generator.generate(104730).size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeLengthThrowsIllegalArgumentException() {
		generator.generate(-1);
	}

	/**
	 * Creates the generator to be tested.
	 * 
	 * @return the newly instantiated generator
	 */
	protected abstract Generator createGenerator();
}
