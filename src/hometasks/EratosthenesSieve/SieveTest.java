package hometasks.EratosthenesSieve;

import static org.junit.Assert.*;
import org.junit.Test;
import hometasks.Exceptions.WrongArgumentException;

public class SieveTest {

	@Test
	public void getPrimesTest() {
		assertEquals("[2]", new Sieve(2).getPrimes().toString());
		assertEquals("[2, 3]", new Sieve(3).getPrimes().toString());
		assertEquals("[2, 3, 5, 7]", new Sieve(10).getPrimes().toString());
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29]", new Sieve(30).getPrimes().toString());
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorExceptionTest1() {
		new Sieve(-15);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorExceptionTest2() {
		new Sieve(1);
	}

}
