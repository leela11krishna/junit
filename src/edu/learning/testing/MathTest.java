package edu.learning.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
@Tag("development")
class MathTest {

	@Test
	void mathMinTest() {
		int actual = Math.min(-233, 333);
		int expected = -233;
		assertEquals(expected, actual);
	}

	@Test
	void mathMaxTest() {
		int actual = Math.max(233, 333);
		int expected = 333;
		assertEquals(expected, actual);
	}

}
