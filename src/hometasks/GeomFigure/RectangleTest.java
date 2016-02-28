package hometasks.GeomFigure;

import static org.junit.Assert.*;
import org.junit.Test;
import hometasks.Exceptions.*;

public class RectangleTest {
	
	@Test
	public void constructorTest() {
		Rectangle rectangle1 = new Rectangle();
		assertEquals(0.0, rectangle1.getWidth(), 0.00001);
		assertEquals(0.0, rectangle1.getHeight(), 0.00001);
		rectangle1 = new Rectangle(5.35, 8.7);
		assertEquals(5.35, rectangle1.getWidth(), 0.00001);
		assertEquals(8.7, rectangle1.getHeight(), 0.00001);
	}
	
	@Test
	public void copyConstructorTest() {
		Rectangle rectangle1 = new Rectangle(5.35, 8.7);
		Rectangle rectangle2 = new Rectangle(rectangle1);
		assertEquals(rectangle1.getWidth(), rectangle2.getWidth(), 0.00001);
		assertEquals(rectangle1.getHeight(), rectangle2.getHeight(), 0.00001);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorWidthExceptionTest() {
		new Rectangle(-5.35, 4.2);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorHeightExceptionTest() {
		new Rectangle(5.35, -4.2);
	}
	
	@Test
	public void setWidthTest() {
		Rectangle rectangle1 = new Rectangle(5.35, 8.7);
		rectangle1.setWidth(4.68);
		assertEquals(4.68, rectangle1.getWidth(), 0.00001);
		assertEquals(8.7, rectangle1.getHeight(), 0.00001);
	}
	
	@Test
	public void setHeightTest() {
		Rectangle rectangle1 = new Rectangle(5.35, 8.7);
		rectangle1.setHeight(4.68);
		assertEquals(5.35, rectangle1.getWidth(), 0.00001);
		assertEquals(4.68, rectangle1.getHeight(), 0.00001);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setWidthExceptionTest() {
		Rectangle rectangle1 = new Rectangle(5.35, 8.7);
		rectangle1.setWidth(-4.68);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setHeightExceptionTest() {
		Rectangle rectangle1 = new Rectangle(5.35, 8.7);
		rectangle1.setHeight(-4.68);
	}

	@Test
	public void getAreaTest() {
		Rectangle square1 = new Rectangle(5);
		assertEquals(25.0, square1.getArea(), 0.00001);
		square1 = new Rectangle(8.5);
		assertEquals(72.25, square1.getArea(), 0.00001);
		square1 = new Rectangle(45.78);
		assertEquals(2095.8084, square1.getArea(), 0.00001);
		Rectangle rectangle1 = new Rectangle(5.9, 6.3);
		assertEquals(37.17, rectangle1.getArea(), 0.00001);
		rectangle1 = new Rectangle(14.36, 12.8);
		assertEquals(183.808, rectangle1.getArea(), 0.00001);
		rectangle1 = new Rectangle(0.97, 5.12);
		assertEquals(4.9664, rectangle1.getArea(), 0.00001);
	}

}
