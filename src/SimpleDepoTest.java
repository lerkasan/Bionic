package hometasks;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class SimpleDepoTest {

	@Test
	public void getInterestTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		SimpleDepo deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		assertEquals(73.84, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2014, 9, 8),20);
		assertEquals(8.22, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setOpenDate(LocalDate.of(2014, 9, 12));
		assertEquals(73.97, deposit1.getInterest(), 0.00001);
		System.out.println();
	}
	
	@Test
	public void getInterestForBigSumTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		SimpleDepo deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2012, 9, 8), 20);
		assertEquals(8.2, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(30);
		deposit1.setSum(60000);
		assertEquals(786.89, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setOpenDate(LocalDate.of(2014, 2, 8));
		assertEquals(789.04, deposit1.getInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setSum(100001);
		deposit1.setOpenDate(LocalDate.of(2014, 5, 12));
		assertEquals(8383.65, deposit1.getInterest(), 0.00001);
		System.out.println();
	}
	
	@Test
	public void getComplexInterestTest() {
		//SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong)
		SimpleDepo deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2013, 9, 8), 20);
		assertEquals(8.22, deposit1.getComplexInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(30);
		assertEquals(12.35, deposit1.getComplexInterest(), 0.00001);
		System.out.println();
		
		deposit1.setDayLong(180);
		deposit1.setOpenDate(LocalDate.of(2014, 5, 12));
		assertEquals(76.32, deposit1.getComplexInterest(), 0.00001);
		System.out.println();
	}
		
}
