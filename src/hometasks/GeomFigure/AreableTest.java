package hometasks.GeomFigure;

import static org.junit.Assert.*;

import org.junit.Test;

public class AreableTest {

	@Test
	public void getTotalAreableTest() {		
		Areable[] areables = new Areable[15];
		areables[0] = new Rectangle(5);
		areables[1]= new Rectangle(8.5);
		areables[2] = new Rectangle(45.78);
		areables[3] = new Rectangle(5.9, 6.3);
		areables[4] = new Rectangle(14.36, 12.8);
		areables[5] = new Rectangle(0.97, 5.12);
		areables[6] = new Rectangle(2.7, 68.9);
		areables[7] = new Rectangle(0.5, 30);
		areables[8] = new Circle(5.35);
		areables[9] = new Circle(9.4);
		areables[10] = new Circle(2.67);
		areables[11] = new Circle(10.1);
		areables[12] = new Circle(14.8);
		areables[13] = new Circle(6.38);
		
		assertEquals(4146.425227910528, Areable.getTotalArea(areables), 0.00001);
	}

}
