package hometasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestComplex {

	@Test
	public void testModule() {
		Complex test1 = new Complex();
		test1.setRe(3.0);
		test1.setIm(4.0);
		double res =test1.getModule();
		if (res != 5.0) {
			fail("Not yet implemented");
		}
	}

}
