package hometasks.DepoBase;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class DepoTest {
	
	@Test
	public void getInterestTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		DepoBase deposit1 = new DepoBase(1000.0, 15.0, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		assertEquals(73.84, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1 = new DepoBase(1000.0, 15.0, LocalDate.of(2014, 9, 8),20);
		assertEquals(8.22, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setStartDate(LocalDate.of(2014, 9, 12));
		assertEquals(73.97, deposit1.getInterest(), 0.00001);
		System.out.println();
	}
	
	@Test
	public void getInterestForBigSumTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		DepoBarrier deposit1 = new DepoBarrier(1000.0, 15.0, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(30);
		deposit1.setSum(60000);
		assertEquals(786.89, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setStartDate(LocalDate.of(2014, 2, 8));
		assertEquals(789.04, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setSum(100001);
		deposit1.setStartDate(LocalDate.of(2014, 5, 12));
		assertEquals(8383.65, deposit1.getInterest(), 0.00001);
		System.out.println();
	}
	
	@Test
	public void getComplexInterestTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		DepoMonthCapitalize deposit1 = new DepoMonthCapitalize(1000.0, 15.0, LocalDate.of(2013, 9, 8), 20);
		assertEquals(8.22, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(30);
		assertEquals(12.36, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setStartDate(LocalDate.of(2014, 5, 12));
		assertEquals(76.33, deposit1.getInterest(), 0.00001);
		System.out.println();
	}

	@Test
	public void totalInterestTest() {
		InterestInterface depos[] = new InterestInterface[6];
		//DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
		depos[0] = (InterestInterface) new DepoBase(1000,15,LocalDate.of(2013,9,8),20);
		depos[1] = (InterestInterface) new DepoBase(2500,18,LocalDate.of(2013,9,8),20);
		depos[2] = (InterestInterface) new DepoBarrier(15000,11.5,LocalDate.of(2013,9,8),40);
		depos[3] = (InterestInterface) new DepoBarrier(5000,14,LocalDate.of(2013,9,8),80);
		depos[4] = (InterestInterface) new DepoMonthCapitalize(2000,16.5,LocalDate.of(2013,9,8),180);
		System.out.println("");
		depos[5] = (InterestInterface) new DepoMonthCapitalize(40000,12.1,LocalDate.of(2013,9,8),91);
		
		double totalInterest = 0;
		try {
			for (InterestInterface depo : depos) {
				System.out.println(depo.getInterest());
				totalInterest += depo.getInterest();
			}
		}
		catch (Exception e) { //TO DO
		}
		int temp = (int)Math.round(totalInterest*100);
		totalInterest = (double)temp/100;
		
		assertEquals(1763.43, totalInterest, 0.00001);
	}
}
