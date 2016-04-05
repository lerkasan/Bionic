package hometasks.NaturalNumber;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import hometasks.Exceptions.*;

public class NaturalNumberTest {

	@Test
	public void constructorTest() {
		NaturalNumber numb = new NaturalNumber();
		assertEquals(1,numb.getNum());
		numb = new NaturalNumber(25);
		assertEquals(25,numb.getNum());
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorExceptionTest() {
		new NaturalNumber(-15);
	}
	
	@Test
	public void copyConstructorTest() {
		NaturalNumber numb1 = new NaturalNumber(201);
		NaturalNumber numb2 = new NaturalNumber(numb1);
		assertEquals(numb1, numb2);
		assertEquals(numb1.getNum(), numb2.getNum());
	}
	
	@Test
	public void equalsTest() {
		NaturalNumber numb1 = new NaturalNumber(201);
		numb1.getAllDivs();
		numb1.getPrimeDivs();
		NaturalNumber numb2 = numb1.copy();
		NaturalNumber numb3 = new NaturalNumber(165);
		assertTrue(numb2.equals(numb1));
		assertTrue(numb1.equals(numb1));
		assertFalse(numb2.equals(null));
		assertFalse(numb2.equals(numb3));
	}
	
	@Test
	public void copyMethodTest() {
		NaturalNumber numb1 = new NaturalNumber(201);
		numb1.getAllDivs();
		numb1.getPrimeDivs();
		NaturalNumber numb2 = numb1.copy();
		assertEquals(numb1, numb2);
		assertEquals(numb1.getNum(), numb2.getNum());
		assertEquals(numb1.getAllDivs().length, numb2.getAllDivs().length);
		assertEquals(numb1.getPrimeDivs().length, numb2.getPrimeDivs().length);
		assertTrue(numb2.equals(numb1));
	}
	
	@Test
	public void getNumTest() {
		NaturalNumber numb = new NaturalNumber(201);
		assertEquals(201,numb.getNum());
	}
	
	@Test
	public void setNumTest() {
		NaturalNumber numb = new NaturalNumber(201);
		numb.setNum(148);
		assertEquals(148,numb.getNum());
	}
	
	@Test
	public void getAllDivsTest() {
		NaturalNumber numb = new NaturalNumber();
		long[] expected = {1};
		assertTrue(Arrays.equals(expected, numb.getAllDivs()));
		NaturalNumber numb1 = new NaturalNumber(128);
		long[] expected1 = {1, 128, 2, 64, 4, 32, 8, 16};
		assertTrue(Arrays.equals(expected1, numb1.getAllDivs()));
		numb.setNum(3);
		long[] expected2 = {1, 3};
		assertTrue(Arrays.equals(expected2, numb.getAllDivs()));
		numb.setNum(17);
		long[] expected3 = {1, 17};
		assertTrue(Arrays.equals(expected3, numb.getAllDivs()));
		numb.setNum(5);
		long[] expected4 = {1, 5};
		assertTrue(Arrays.equals(expected4, numb.getAllDivs()));
		numb.setNum(20);
		long[] expected5 = {1, 20, 2, 10, 4, 5};
		assertTrue(Arrays.equals(expected5, numb.getAllDivs()));
		numb.setNum(36);
		long[] expected6 = {1, 36, 2, 18, 3, 12, 4, 9, 6};
		assertTrue(Arrays.equals(expected6, numb.getAllDivs()));
		numb.setNum(70);
		long[] expected7 = {1, 70, 2, 35, 5, 14, 7, 10};
		assertTrue(Arrays.equals(expected7, numb.getAllDivs()));
		numb.setNum(125);
		long[] expected8 = {1, 125, 5, 25};
		assertTrue(Arrays.equals(expected8, numb.getAllDivs()));
	}
	
	@Test
	public void getPrimeDivsTest() {
		NaturalNumber numb = new NaturalNumber();
		long[] expected = {};
		assertTrue(Arrays.equals(expected, numb.getPrimeDivs()));
		NaturalNumber numb1 = new NaturalNumber(128);
		long[] expected1 = {2};
		assertTrue(Arrays.equals(expected1, numb1.getPrimeDivs()));
		numb.setNum(3);
		long[] expected2 = {3};
		assertTrue(Arrays.equals(expected2, numb.getPrimeDivs()));
		numb.setNum(17);
		long[] expected3 = {17};
		assertTrue(Arrays.equals(expected3, numb.getPrimeDivs()));
		numb.setNum(5);
		long[] expected4 = {5};
		assertTrue(Arrays.equals(expected4, numb.getPrimeDivs()));
		numb.setNum(20);
		long[] expected5 = {2, 5};
		assertTrue(Arrays.equals(expected5, numb.getPrimeDivs()));
		numb.setNum(36);
		long[] expected6 = {2, 3};
		assertTrue(Arrays.equals(expected6, numb.getPrimeDivs()));
		numb.setNum(70);
		long[] expected7 = {2, 5, 7};
		assertTrue(Arrays.equals(expected7, numb.getPrimeDivs()));
		numb.setNum(125);
		long[] expected8 = {5};
		assertTrue(Arrays.equals(expected8, numb.getPrimeDivs()));
		numb.setNum(840);
		long[] expected9 = {2, 3, 5, 7};
		assertTrue(Arrays.equals(expected9, numb.getPrimeDivs()));
		numb.setNum(242);
		long[] expected10 = {2, 11};
		assertTrue(Arrays.equals(expected10, numb.getPrimeDivs()));
	}
	
	@Test
	public void isPrimeTest() {
		NaturalNumber numb = new NaturalNumber(1);
		assertFalse(numb.isPrime());
		numb.setNum(2);
		assertTrue(numb.isPrime());
		numb.setNum(4);
		assertFalse(numb.isPrime());
		numb.setNum(6);
		assertFalse(numb.isPrime());
		numb.setNum(9);
		assertFalse(numb.isPrime());
		numb.setNum(10);
		assertFalse(numb.isPrime());
		numb.setNum(25);
		assertFalse(numb.isPrime());
		numb.setNum(121);
		assertFalse(numb.isPrime());
		numb.setNum(3);
		assertTrue(numb.isPrime());
		numb.setNum(5);
		assertTrue(numb.isPrime());
		numb.setNum(7);
		assertTrue(numb.isPrime());
		numb.setNum(17);
		assertTrue(numb.isPrime());
		numb.setNum(29);
		assertTrue(numb.isPrime());
		numb.setNum(31);
		assertTrue(numb.isPrime());
	}
	
	@Test
	public void isPerfectTest() {
		NaturalNumber numb = new NaturalNumber(6);
		assertTrue(numb.isPerfect());
		numb.setNum(28);
		assertTrue(numb.isPerfect());
		numb.setNum(496);
		assertTrue(numb.isPerfect());
		numb.setNum(8128);
		assertTrue(numb.isPerfect());
		numb.setNum(33550336);
		assertTrue(numb.isPerfect());
		numb.setNum(128);
		assertFalse(numb.isPerfect());
		numb.setNum(357);
		assertFalse(numb.isPerfect());
		numb.setNum(1024);
		assertFalse(numb.isPerfect());
		numb.setNum(963);
		assertFalse(numb.isPerfect());
		numb.setNum(25);
		assertFalse(numb.isPerfect());
	}
	
	@Test
	public void findPerfectsTest() {
		long[] expected = {6, 28, 496, 8128, 33550336};
		assertTrue(Arrays.equals(expected, NaturalNumber.findPerfects(5)));
	}
}
