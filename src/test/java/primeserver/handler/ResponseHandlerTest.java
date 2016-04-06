package primeserver.handler;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import primeserver.generator.Generator;

public class ResponseHandlerTest {
	/** Mock algorithm name. */
	private static final String MOCK_ALGO_NAME = "_mockAlgo_";

	/** Test target. */
	protected ResponseHandler responseHandler;

	// mocks
	protected Generator mockGenerator;
	protected GeneratorRegistry mockGeneratorRegistry;

	@Before
	public void setUpMocks() {
		mockGenerator = mock(Generator.class);
		when(mockGenerator.getAlgorithmName()).thenReturn(MOCK_ALGO_NAME);
		mockGeneratorRegistry = mock(GeneratorRegistry.class);
	}

	@Test
	public void dataIsGeneratedForDefaultAlgorithm() {
		// given
		responseHandler = new ResponseHandler(mockGeneratorRegistry);

		Integer[] expected = new Integer[] { 5, 6, 7, 8 };
		when(mockGenerator.generate(100)).thenReturn(new TreeSet<Integer>(Arrays.asList(expected)));
		when(mockGeneratorRegistry.getGenerator(null)).thenReturn(mockGenerator);

		// when
		Map<String, Object> result = responseHandler.handle("100", null);
		Integer[] actual = (Integer[]) result.get("data");

		// then
		assertArrayEquals(expected, actual);
	}

	@Test
	public void metadataIsGeneratedForDefaultAlgorithm() {
		// given
		responseHandler = new ResponseHandler(mockGeneratorRegistry);

		Integer[] expected = new Integer[] { 5, 6, 7, 8 };
		when(mockGenerator.generate(100)).thenReturn(new TreeSet<Integer>(Arrays.asList(expected)));
		when(mockGeneratorRegistry.getGenerator(null)).thenReturn(mockGenerator);

		// when
		Map<String, Object> result = responseHandler.handle("100", null);

		// then
		assertEquals("OK", result.get("status"));
		assertEquals(100, result.get("max"));
		assertEquals(MOCK_ALGO_NAME, result.get("algorithm"));
		assertNotNull(result.get("time"));
	}

	@Test
	public void errorIsCorrectlyGenerated() {
		// given
		responseHandler = new ResponseHandler(mockGeneratorRegistry);

		String expected = "_testError_";
		when(mockGenerator.generate(anyInt())).thenThrow(new RuntimeException(expected));
		when(mockGeneratorRegistry.getGenerator(null)).thenReturn(mockGenerator);

		// when
		Map<String, Object> result = responseHandler.handle("100", null);

		// then
		assertEquals("error", result.get("status"));
		assertEquals(expected, result.get("message"));
	}
}
