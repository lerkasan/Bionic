package hometasks.DepoBase;

import static org.junit.Assert.*;
import java.util.List;

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
	     List<DepoBase> arrayList = depoList.createListWithoutMinimalSum();
	     assertEquals(0, arrayList.stream()
	    		 .filter(d -> d.getSum() < DepoList.MINIMAL_SUM)
	    		 .count());
	}
	
	@Test
	public void saveAndReadSerializedTest() {
		DepoList depoList1 = new DepoList();
		DepoList depoList2 = new DepoList();
		depoList1.init();
		depoList2.init();
		depoList1.saveSerializedToFile("results\\serializedDepo1.txt");
		depoList2.saveSerializedToFile("results\\serializedDepo2.txt");
		DepoList depoList3 = DepoList.readSerializedFromFile("results\\serializedDepo1.txt");
		DepoList depoList4 = DepoList.readSerializedFromFile("results\\serializedDepo2.txt");
		assertEquals(depoList1, depoList3);
		assertEquals(depoList1.getList(), depoList3.getList());
		assertEquals(depoList2, depoList4);
		assertEquals(depoList2.getList(), depoList3.getList());

	}
}
