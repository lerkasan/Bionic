package hometasks.GeomFigure;

import static org.junit.Assert.*;
import org.junit.Test;
import hometasks.Exceptions.*;

public class CircleTest {
	
	@Test
	public void constructorTest() {
		Circle circle1 = new Circle();
		assertEquals(0.0, circle1.getRadius(), 0.00001);
		circle1 = new Circle(5.35);
		assertEquals(5.35, circle1.getRadius(), 0.00001);
	}
	
	@Test
	public void copyConstructorTest() {
		Circle circle1 = new Circle(6.8);
		Circle circle2 = new Circle(circle1);
		assertEquals(circle1.getRadius(), circle2.getRadius(), 0.00001);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorExceptionTest() {
		new Circle(-5.35);
	}
	
	@Test
	public void setRadiusTest() {
		Circle circle1 = new Circle(5.35);
		circle1.setRadius(4.68);
		assertEquals(4.68, circle1.getRadius(), 0.00001);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setRadiusExceptionTest() {
		Circle circle1 = new Circle(5.35);
		circle1.setRadius(-4.68);
	}
	
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
