package hometasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void getAreaTest() {
		Circle circle1 = new Circle(5.35);
		assertEquals(89.920236, circle1.getArea(), 0.00001);
		circle1 = new Circle(9.4);
		assertEquals(277.591127, circle1.getArea(), 0.00001);
		circle1 = new Circle(2.67);
		assertEquals(22.3960999, circle1.getArea(), 0.00001);
		circle1 = new Circle(10.1);
		assertEquals(320.473867, circle1.getArea(), 0.00001);
		circle1 = new Circle(14.8);
		assertEquals(688.13445, circle1.getArea(), 0.00001);
	}

}
