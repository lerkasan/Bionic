package hometasks.DepoBase;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepoListTest {

	@Test
	public void getPrincipalTest() {
	     DepoList list = new DepoList();
	     list.init();
	     assertEquals(73000.0, list.getPrincipal(), 0.00005);
	}
}
