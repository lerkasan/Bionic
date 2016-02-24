package hometasks.Factorial;

import static org.junit.Assert.*;
import org.junit.Test;

public class FactorialTest {

	@Test
	public void getBigIntegerFactorialTest() {
		assertEquals("15511210043330985984000000", Factorial.getBigIntegerFactorial(25).toString());
	}

}
