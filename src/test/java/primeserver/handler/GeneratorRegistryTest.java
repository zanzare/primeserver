package primeserver.handler;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import primeserver.generator.Generator;
import primeserver.generator.GeneratorFactory;

public class GeneratorRegistryTest {
	// mocks
	protected Generator mockTrialDivisionGenerator;
	protected Generator mockSieveOfEratosthenesGenerator;
	protected Generator mockSieveOfSundaramGenerator;
	protected GeneratorFactory mockGf;

	@Before
	public void setUpMocks() {
		mockTrialDivisionGenerator = mock(Generator.class);
		when(mockTrialDivisionGenerator.getAlgorithmName()).thenReturn("TrialDivision");
		mockSieveOfEratosthenesGenerator = mock(Generator.class);
		when(mockSieveOfEratosthenesGenerator.getAlgorithmName()).thenReturn("SieveOfEratosthenes");
		mockSieveOfSundaramGenerator = mock(Generator.class);
		when(mockSieveOfSundaramGenerator.getAlgorithmName()).thenReturn("SieveOfSundaram");

		mockGf = mock(GeneratorFactory.class);
		when(mockGf.createTrialDivisionGenerator()).thenReturn(mockTrialDivisionGenerator);
		when(mockGf.createSieveOfEratosthenesGenerator()).thenReturn(mockSieveOfEratosthenesGenerator);
		when(mockGf.createSieveOfSundaramGenerator()).thenReturn(mockSieveOfSundaramGenerator);
	}

	@Test
	public void specifiedGeneratorIsSelected() {
		// given
		GeneratorRegistry generatorRegistry = new GeneratorRegistry(mockGf);

		// when
		Generator actual = generatorRegistry.getGenerator("sieveOfEratosthenes");

		// then
		assertSame(mockSieveOfEratosthenesGenerator, actual);
	}

	@Test
	public void defaultGeneratorIsSelected() {
		// given
		GeneratorRegistry generatorRegistry = new GeneratorRegistry(mockGf);

		// when
		Generator actual = generatorRegistry.getGenerator(null);

		// then
		assertSame(mockSieveOfSundaramGenerator, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidAlgorithmThrowsException() {
		GeneratorRegistry generatorRegistry = new GeneratorRegistry(mockGf);
		generatorRegistry.getGenerator("_nonexistent_");
	}
}
