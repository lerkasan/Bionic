package hometasks;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

public class BirthdayTest {

	@Test
	public void constructorTest() {
		Birthday bDay = new Birthday();
		assertEquals(LocalDate.now(),bDay.getBirthDate());
		bDay = new Birthday(LocalDate.of(1991, 8, 24));
		assertEquals(LocalDate.of(1991, 8, 24),bDay.getBirthDate());
	}
	
	@Test
	public void copyConstructorTest() {
		Birthday bDay1 = new Birthday(LocalDate.of(1991, 8, 24));
		Birthday bDay2 = new Birthday(bDay1);
		assertEquals(bDay1.getBirthDate(),bDay2.getBirthDate());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorExceptionTest() {
		new Birthday(LocalDate.now().plusYears(5));
	}
	
	@Test
	public void copyMethodTest() {
		Birthday bDay1 = new Birthday(LocalDate.of(1991, 8, 24));
		Birthday bDay2 = bDay1.copy();
		assertEquals(bDay1.getBirthDate(),bDay2.getBirthDate());
	}
	
	@Test
	public void isEqualTest() {
		Birthday bDay1 = new Birthday(LocalDate.of(1991, 8, 24));
		Birthday bDay2 = bDay1.copy();
		Birthday bDay3 = new Birthday(LocalDate.of(2004, 6, 15));
		assertTrue(bDay2.isEqual(bDay1));
		assertTrue(bDay1.isEqual(bDay1));
		assertFalse(bDay1.isEqual(bDay3));
		assertFalse(bDay1.isEqual(null));
	}
	
	@Test
	public void getBirthDateTest() {
		Birthday bDay = new Birthday(LocalDate.of(1991, 8, 24));
		assertEquals(LocalDate.of(1991, 8, 24),bDay.getBirthDate());
	}
	
	@Test
	public void setBirthDateTest() {
		Birthday bDay = new Birthday(LocalDate.of(1991, 8, 24));
		bDay.setBirthDate(LocalDate.of(2012, 5, 19));
		assertEquals(LocalDate.of(2012, 5, 19),bDay.getBirthDate());
	}
	
	@Test
	public void getBirthDayOfWeekTest() {
		Birthday bDay = new Birthday(LocalDate.of(1991, 8, 24));
		assertEquals(bDay.getBirthDate().getDayOfWeek(),bDay.getBirthDayOfWeek());
	}
	
	@Test
	public void getDaysAge() {
		Birthday bDay = new Birthday(LocalDate.now());
		assertEquals(0,bDay.getDaysAge());
		bDay = new Birthday(LocalDate.now().minusDays(12));
		assertEquals(12,bDay.getDaysAge());
		bDay = new Birthday(LocalDate.now().minusDays(125));
		assertEquals(125,bDay.getDaysAge());
	}
	
	@Test
	public void getMonthsAge() {
		Birthday bDay = new Birthday(LocalDate.now());
		assertEquals(0,bDay.getMonthsAge());
		bDay = new Birthday(LocalDate.now().minusDays(12));
		assertEquals(0,bDay.getMonthsAge());
		bDay = new Birthday(LocalDate.now().minusDays(130));
		assertEquals(4,bDay.getMonthsAge());
		bDay = new Birthday(LocalDate.now().minusMonths(10));
		assertEquals(10,bDay.getMonthsAge());
		bDay = new Birthday(LocalDate.now().minusMonths(28));
		assertEquals(28,bDay.getMonthsAge());
		bDay = new Birthday(LocalDate.now().minusYears(2));
		assertEquals(24,bDay.getMonthsAge());
	}
}
