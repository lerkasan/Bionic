package hometasks.Factorial;

import static org.junit.Assert.*;
import org.junit.Test;

public class FactorialTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void getBigIntegerFactorialExceptionTest() {
		Factorial.getBigIntegerFactorial(-12);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getLongFactorialExceptionTest() {
		Factorial.getLongFactorial(-12);
	}

	@Test
	public void getBigIntegerFactorialTest() {
		assertEquals("2432902008176640000", Factorial.getBigIntegerFactorial(20).toString());
		assertEquals("15511210043330985984000000", Factorial.getBigIntegerFactorial(25).toString());
	}
	
	@Test
	public void getLongFactorialTest() {
		assertEquals(2432902008176640000L, Factorial.getLongFactorial(20));
	}
}
