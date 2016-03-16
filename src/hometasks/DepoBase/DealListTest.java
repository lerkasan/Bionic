package hometasks.DepoBase;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class DealListTest {

	@Test
	public void compareIncomeTest() {
	    DealList<Incomable> list1 = new DealList<>();
	    DealList<Incomable> list2 = new DealList<>();
	    DealList<Incomable> list3 = new DealList<>();
	    //DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
	    //TBill(double nominal, double price, int billsAmount, LocalDate maturityDate)
	    
	    list1.add(new DepoMonthCapitalize(10000.0, 21.0, LocalDate.of(2012, 2, 1), 181));
	    list1.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    list1.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    list1.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	    list1.add(new TBill(500, 495.87, 195, LocalDate.of(2011, 12, 18)));
	    list1.add(new TBill(400, 380.75, 1024, LocalDate.of(2011, 12, 18)));
	    
	    list2.add(new DepoBase(3500.0, 19.5, LocalDate.of(2013, 9, 8), 120));
	    list2.add(new DepoMonthCapitalize(12000.0, 22.0, LocalDate.of(2012, 2, 1), 281));
	    list2.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    list2.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    list2.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	    list2.add(new TBill(500, 490.87, 195, LocalDate.of(2011, 12, 18)));
	    list2.add(new TBill(1400, 1320.75, 1024, LocalDate.of(2011, 12, 18)));
	    
	    DepoMonthCapitalize depo1 = new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91);
	    TBill bill1 = new TBill(200,190.51,550,LocalDate.of(2013,9,8));
	    list3.add(depo1);
	    //System.out.println("depo "+depo1.getIncome()+";  bill "+bill1.getIncome());
		assertEquals(-1, list1.compareIncome(list2));
		assertEquals(1, list2.compareIncome(list1));
		assertEquals(0, list1.compareIncome(list1));
		assertEquals(0, list3.compareIncome(depo1));
		assertEquals(-1, list3.compareIncome(bill1));
	    
	}

}
