package hometasks.GeomFigure;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

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
