package hometasks.DepoBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DepoListTest {

	@Test
	public void getPrincipalTest() {
	     DepoList list = new DepoList();
	     list.init();
	     assertEquals(73000.0, list.getPrincipal(), 0.00005);
	}
	
	@Test
	public void removeTest() {
	     DepoList depoList = new DepoList();
	     depoList.init();
	     depoList.remove();
	     assertEquals(0, depoList.getList().stream()
	    		 .filter(d -> d.getSum() < DepoList.MINIMAL_SUM)
	    		 .count());
	}
	
	@Test
	public void createListWithoutMinimalSumTest() {
	     DepoList depoList = new DepoList();
	     depoList.init();
	     ArrayList<DepoBase> arrayList = depoList.createListWithoutMinimalSum();
	     assertEquals(0, arrayList.stream()
	    		 .filter(d -> d.getSum() < DepoList.MINIMAL_SUM)
	    		 .count());
	}
}
