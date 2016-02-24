package hometasks;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;

public class HolidaysTest {

	@Test
	public void constructorTest() {
		Holidays holiday = new Holidays(2014);
		assertEquals(2014, holiday.getYear());
		
		holiday = new Holidays();
		assertEquals(LocalDate.now().getYear(), holiday.getYear());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionTest() {
		new Holidays(-2050);
	}
	
	@Test
	public void copyConstructorTest() {
		Holidays holiday1 = new Holidays(2015);
		Holidays holiday2 = new Holidays(holiday1);
		assertEquals(holiday1.getYear(), holiday2.getYear());
	}
	
	@Test
	public void getYearTest() {
		Holidays holiday = new Holidays(2012);
		assertEquals(2012, holiday.getYear());
	}
	
	@Test
	public void setYearTest() {
		Holidays holiday = new Holidays(2005);
		holiday.setYear(2010);
		assertEquals(2010, holiday.getYear());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setYearExceptionTest() {
		Holidays holiday = new Holidays(2005);
		holiday.setYear(-210);
	}
	
	@Test
	public void converJulianToGregorianTest() {
		assertEquals(LocalDate.of(1605, 9, 20),
				Holidays.convertJulianToGregorian(LocalDate.of(1605, 9, 10)));
		assertEquals(LocalDate.of(1710, 6, 21),
				Holidays.convertJulianToGregorian(LocalDate.of(1710, 6, 10)));
		assertEquals(LocalDate.of(1800, 4, 22), 
				Holidays.convertJulianToGregorian(LocalDate.of(1800, 4, 10)));
		assertEquals(LocalDate.of(1991, 8, 23),
				Holidays.convertJulianToGregorian(LocalDate.of(1991, 8, 10)));
		assertEquals(LocalDate.of(2016, 2, 23),
				Holidays.convertJulianToGregorian(LocalDate.of(2016, 2, 10)));
		assertEquals(LocalDate.of(2100, 5, 24), 
				Holidays.convertJulianToGregorian(LocalDate.of(2100, 5, 10)));
		assertEquals(LocalDate.of(2200, 7, 25),
				Holidays.convertJulianToGregorian(LocalDate.of(2200, 7, 10)));
		assertEquals(LocalDate.of(1415, 10, 10),
				Holidays.convertJulianToGregorian(LocalDate.of(1415, 10, 10)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void converJulianToGregorianExceptionTest() {
		Holidays.convertJulianToGregorian(LocalDate.of(2585, 9, 14));
	}
	
	@Test
	public void converToGregorianSince1918Test() {
		assertEquals(LocalDate.of(1848, 2, 10), 
				Holidays.convertToGregorianSince1918(LocalDate.of(1848, 2, 10)));
		assertEquals(LocalDate.of(1918, 2, 23),
				Holidays.convertToGregorianSince1918(LocalDate.of(1918, 2, 10)));
		assertEquals(LocalDate.of(2016, 6, 23),
				Holidays.convertToGregorianSince1918(LocalDate.of(2016, 6, 10)));
	}
	
	@Test
	public void getEasterTest() {
		Holidays holiday = new Holidays(2016);
		assertEquals(LocalDate.of(2016, 5, 1), holiday.getEaster());
		holiday.setYear(2004);
		assertEquals(LocalDate.of(2004, 4, 11), holiday.getEaster());
		holiday.setYear(2013);
		assertEquals(LocalDate.of(2013, 5, 5), holiday.getEaster());
	}
	
	@Test
	public void getTrinityTest() {
		Holidays holiday = new Holidays(2016);
		assertEquals(holiday.getEaster().plusDays(49), holiday.getTrinity());
		assertEquals(LocalDate.of(2016, 6, 19), holiday.getTrinity());
		
		holiday.setYear(2015);
		assertEquals(holiday.getEaster().plusDays(49), holiday.getTrinity());
		assertEquals(LocalDate.of(2015, 5, 31), holiday.getTrinity());
		
		holiday.setYear(2004);
		assertEquals(holiday.getEaster().plusDays(49), holiday.getTrinity());
		
		holiday.setYear(2013);
		assertEquals(holiday.getEaster().plusDays(49), holiday.getTrinity());
	}
	
	@Test
	public void copyMethodTest() {
		Holidays holiday1 = new Holidays(2000);
		holiday1.getEaster();
		holiday1.getTrinity();
		
		Holidays holiday2 = new Holidays(holiday1);
		assertEquals(holiday1.getEaster(), holiday2.getEaster());
		assertEquals(holiday1.getTrinity(),holiday2.getTrinity());
	}
	
	@Test
	public void isEqualMethodTest() {
		Holidays holiday1 = new Holidays(2012);
		assertTrue(holiday1.isEqual(holiday1));
		holiday1.getEaster();
		holiday1.getTrinity();
		assertTrue(holiday1.isEqual(holiday1));
		
		Holidays holiday2 = new Holidays(2015);
		Holidays holiday3 = new Holidays(2001);
		holiday2.getEaster();
		holiday2.getTrinity();
		holiday2.setYear(2012);
		holiday2.getEaster();
		holiday2.getTrinity();
		assertTrue(holiday1.isEqual(holiday2));
		assertFalse(holiday1.isEqual(null));
		assertFalse(holiday1.isEqual(holiday3));
	}
}
