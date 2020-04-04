package edu.learning.testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 
 * Writing some test cases for string
 * 
 * @author lgolla2
 *
 */
class StringTest {

	private String str;

	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize database connections");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("Close database connections");
	}

	/**
	 * 
	 * @Before is used in JUnit 4, also TestInfo is not there in JUnit 4.
	 * 
	 *         Also we don't need public access modifier in JUnit 5 whereas it is
	 *         need in JUnit 4
	 * 
	 * @param info
	 * 
	 */
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initialize test data for " + info.getDisplayName());
	}

	/**
	 * 
	 * @After is used in JUnit 4
	 * 
	 * @param info
	 * 
	 */
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Clean up test data for " + info.getDisplayName());
	}

	@Test
	void testStringLength() {
		int actual = "ABCD".length();
		int expected = 4;
		assertEquals(expected, actual);
	}

	/**
	 * Used to test the same method with multiple parameters
	 * 
	 * @param str
	 */
	@ParameterizedTest
	@DisplayName("Parameterized tests")
	@ValueSource(strings = { "ABCD", "ABC", "A", "DEF" })
	void testStringLengthUsingParameterizedTest(String str) {
		assertTrue(str.length() > 0);
	}

	// @Test(expected=NullPointerException.class) used in JUnit 4
	@Test
	@DisplayName("When length is null then throw an exception") // From JUnit 5
	void testStringLengthException() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			str.length();
		});
	}

	@Test
	void toUpperCase() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertEquals("ABCD", result);
	}

	/**
	 * Used to test the same method with multiple parameters like key, values
	 * 
	 * @param word
	 * @param capitalizedWord
	 */
	@ParameterizedTest
	// '' use single quotes for empty strings
	@CsvSource(value = { "abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG" })
	void toUpperCaseUsingCsvSource(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
		// assertEquals("ABCD", "abcd".toUpperCase());
		// assertEquals("ABC", "abc".toUpperCase());
		// assertEquals("ABCDEFG", "abcdefg".toUpperCase());
		// assertEquals("", "".toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = { "abcd, 4", "abc, 3", "'',0", "abcdefg, 7" })
	void testLengthUsingCsvSource(String word, int length) {
		assertEquals(length, word.length());
	}

	@Test
	void testContains() {
		String str = "tomcat";
		boolean result = str.contains("lion");
		// assertEquals(false, result);
		assertFalse(result);
	}

	/**
	 * Used for multiple threads testing, asynchronous calls etc.
	 */
	@Test
	@RepeatedTest(10)
	void testContainsRepeat() {
		String str = "tomcat";
		boolean result = str.contains("lion");
		// assertEquals(false, result);
		assertFalse(result);
	}

	@Test
	void testSplit() {
		String str = "I am writing junit test cases";
		String[] actual = str.split(" ");
		String[] expected = new String[] { "I", "am", "writing", "junit", "test", "cases" };
		assertArrayEquals(expected, actual);
	}

	/**
	 * Used to test scenarios with time constraint
	 */
	// @Test(timeout = 5000) for JUnit 4, assertTimeout for JUnit 5
	@Test
	void performanceTest() {
		// should execute within 50 seconds
		assertTimeout(Duration.ofSeconds(5), () -> {
			for (int i = 0; i < 100000; i++) {
				System.out.println(i);
			}
		});
	}

	@Test
	@Disabled // @Ignore for JUnit 4
	void testLowerCase() {
		assertEquals("junit", "JUNIT".toLowerCase());
	}

	/**
	 * Group test cases by similar features
	 * 
	 * @author lgolla2
	 *
	 */
	@Nested
	@Tag("production")
	class EmptyStringTests {
		@BeforeEach
		void setToEmpty() {
			str = "";
		}

		@Test
		@DisplayName("Length should be 0")
		void lengthIsZero() {
			assertEquals(0, str.length());
		}

		@Test
		@DisplayName("Upper case is empty")
		void upperCaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
	}

	@Nested
	@Tag("production")
	class LargeStringTests {
		@BeforeEach
		void setLargeString() {
			str = "Writing some Junit 5 test cases";
		}

		@Test
		void isLargeStringTest() {
			assertTrue(str.length() > 20);
		}
		
		@Test
		void largeStringContains() {
			assertTrue(str.contains("Junit"));
		}
	}

}
