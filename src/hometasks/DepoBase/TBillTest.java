package hometasks.DepoBase;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TBillTest {

	@Test
	public void compareToTest() {
		TBill bill1 = new TBill(2000,1990.51,550,LocalDate.of(2013,9,8));
		TBill bill2 = new TBill(3000,2985.25, 1206,LocalDate.of(2013,9,8));
		assertEquals(-1, bill1.compareIncome(bill2));
		assertEquals(1, bill2.compareIncome(bill1));
		assertEquals(0, bill1.compareIncome(bill1));	
	}

}
