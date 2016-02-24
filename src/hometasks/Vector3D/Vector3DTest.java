package hometasks.Vector3D;

import static org.junit.Assert.*;
import org.junit.Test;

public class Vector3DTest {

	@Test
	public void constructorTest() {
		Vector3D vect = new Vector3D();
		assertEquals(0.0, vect.getX(), 0.00001);
		assertEquals(0.0, vect.getY(), 0.00001);
		assertEquals(0.0, vect.getZ(), 0.00001);
		
		vect = new Vector3D(-5.2, 64.35, 14);
		assertEquals(-5.2, vect.getX(), 0.00001);
		assertEquals(64.35, vect.getY(), 0.00001);
		assertEquals(14.0, vect.getZ(), 0.00001);
	}
	
	@Test
	public void copyConstructorTest() {
		Vector3D vect1 = new Vector3D(0, -58, 14.7);
		Vector3D vect2 = new Vector3D(vect1);
		assertEquals(0, vect2.getX(), 0.00001);
		assertEquals(-58, vect2.getY(), 0.00001);
		assertEquals(14.7, vect2.getZ(), 0.00001);
	}
	
	@Test
	public void copyMethodTest() {
		Vector3D vect1 = new Vector3D(9.99, 0, -190.7);
		Vector3D vect2 = vect1.copy();
		assertEquals(9.99, vect2.getX(), 0.00001);
		assertEquals(0, vect2.getY(), 0.00001);
		assertEquals(-190.7, vect2.getZ(), 0.00001);
	}
	
	@Test
	public void equalsTest() {
		Vector3D vect1 = new Vector3D(-10.5, -15, -1.7);
		Vector3D vect2 = vect1.copy();
		Vector3D vect3 = new Vector3D(-10.5, -15, -1.7);
		Vector3D vect4 = new Vector3D(vect2);
		assertTrue(vect1.equals(vect2));
		assertTrue(vect2.equals(vect3));
		assertTrue(vect3.equals(vect4));
		assertTrue(vect4.equals(vect1));
	}
	
	@Test
	public void toStringTest() {
		Vector3D vect = new Vector3D(-14.51, 15.0, 178.17);
		assertTrue("(-14.51; 15.0; 178.17)".equals(vect.toString()));
		vect = new Vector3D(-14, 0, -18.17);
		assertTrue("(-14.0; 0.0; -18.17)".equals(vect.toString()));
	}
	
	@Test
	public void gettersTest() {
		Vector3D vect = new Vector3D(-5, 64.35, 0);
		assertEquals(-5.0, vect.getX(), 0.00001);
		assertEquals(64.35, vect.getY(), 0.00001);
		assertEquals(0.0, vect.getZ(), 0.00001);
	}
	
	@Test
	public void settersTest() {
		Vector3D vect = new Vector3D(-5, 64.35, 0);
		vect.setX(78.9);
		assertEquals(78.9, vect.getX(), 0.00001);
		assertTrue("(78.9; 64.35; 0.0)".equals(vect.toString()));
		vect.setY(-14.9);
		assertEquals(-14.9, vect.getY(), 0.00001);
		assertTrue("(78.9; -14.9; 0.0)".equals(vect.toString()));
		vect.setZ(0.9);
		assertEquals(0.9, vect.getZ(), 0.00001);
		assertTrue("(78.9; -14.9; 0.9)".equals(vect.toString()));
		vect.setXYZ(5.1, -6, 0);
		assertEquals(5.1, vect.getX(), 0.00001);
		assertEquals(-6.0, vect.getY(), 0.00001);
		assertEquals(0.0, vect.getZ(), 0.00001);
		assertTrue("(5.1; -6.0; 0.0)".equals(vect.toString()));
	}
	
	@Test
	public void addTest() {
		Vector3D vect1 = new Vector3D(-5, 64.35, 0);
		Vector3D vect2 = new Vector3D(5, -60.3, 9);
		Vector3D vect3 = vect1.add(vect2);
		assertEquals(0.0, vect3.getX(), 0.00001);
		assertEquals(4.05, vect3.getY(), 0.00001);
		assertEquals(9.0, vect3.getZ(), 0.00001);
		//assertTrue("(0.0; 4.05; 9.0)".equals(vect3.toString()));
		
		vect1 = new Vector3D(-5, 64.35, 0);
		vect2 = new Vector3D();
		vect3 = vect1.add(vect2);
		assertTrue(vect3.equals(vect1));
	}
	
	@Test
	public void multiplyTest() {
		Vector3D vect1 = new Vector3D(-5.25, 6.5, 0);
		Vector3D vect2 = vect1.multiply(3);
		assertTrue(vect2.equals(new Vector3D(-15.75, 19.5, 0)));
		vect2 = vect1.multiply(0);
		assertTrue(vect2.equals(new Vector3D()));
	}
	
	
	@Test
	public void getScalarProductTest() {
		Vector3D vect1 = new Vector3D(-5, 64.35, 0);
		Vector3D vect2 = new Vector3D(5, -60.3, 9);
		Vector3D vect3 = new Vector3D();
		Vector3D vect4 = new Vector3D(-51.8, 6.3, -19.1);
		assertEquals(0.0, vect1.getScalarProduct(vect3), 0.00001);
		
		//коммутативность a*b = b*a
		assertEquals(vect1.getScalarProduct(vect2), vect2.getScalarProduct(vect1), 0.00001);
		
		//дистрибутивность (a+b)*c =a*c+b*c
		Vector3D vect5 = vect1.add(vect2);
		double scalarOfSum = vect5.getScalarProduct(vect4);
		double scalar1 = vect1.getScalarProduct(vect4);
		double scalar2 = vect2.getScalarProduct(vect4);
		assertEquals(scalarOfSum, scalar1+scalar2, 0.00001);
		
		// ассоциативность (wA)*(uB) = wu(A*B)
		vect1 = new Vector3D(-5, 15, 25);
		vect2 = new Vector3D(4, -8, 16);
		vect3 = new Vector3D(-1, 3, 5);
		vect4 = new Vector3D(1, -2, 4);
		assertEquals(20*vect3.getScalarProduct(vect4), vect1.getScalarProduct(vect2), 0.00001);
		assertEquals(vect3.multiply(5).getScalarProduct(vect2), vect1.getScalarProduct(vect2), 0.00001);
		
		vect1 = new Vector3D(14, 5.25, -9);
		vect2 = new Vector3D(-7.5, -4, 6);
		assertEquals(-180.0, vect1.getScalarProduct(vect2), 0.00001);	
	}
	
	@Test
	public void getVectorProductTest() {
		Vector3D vect1 = new Vector3D(-5, 7.35, 0);
		Vector3D vect2 = new Vector3D(12, -60.3, 9);
		Vector3D vect3 = new Vector3D(1.4, -0.7, -5.8);
		
		// A x B = -B x A 
		Vector3D vect12 = vect1.getVectorProduct(vect2);
		Vector3D vect21 = vect2.getVectorProduct(vect1);
		assertTrue(vect12.equals(vect21.multiply(-1)));
		
		//дистрибутивность (a+b)*c =a*c+b*c
		vect12 = vect1.add(vect2);
		Vector3D vect4 = vect12.getVectorProduct(vect3);
		Vector3D vect13 = vect1.getVectorProduct(vect3);
		Vector3D vect23 = vect2.getVectorProduct(vect3);
		assertTrue(vect4.equals(vect13.add(vect23)));
		assertEquals(vect4.toString(), vect13.add(vect23).toString());
		
		// nA x B = A x nB = n(A x B)
		Vector3D vect5 = vect2.multiply(7).getVectorProduct(vect3);
		Vector3D vect6 = vect2.getVectorProduct(vect3.multiply(7));
		Vector3D vect7 = vect2.getVectorProduct(vect3).multiply(7);
		assertTrue(vect5.equals(vect6));
		assertTrue(vect6.equals(vect7));
		assertTrue(vect7.equals(vect5));
		
		vect1 = new Vector3D(-4.5, 3.75, 1.2);
		vect2 = new Vector3D(-15.4, 21.6, -8.4);
		vect3 = vect1.getVectorProduct(vect2);
		assertTrue(vect3.equals(new Vector3D(-57.42, -56.28, -39.45)));
	}
	
	@Test
	public void getModuleTest() {
		Vector3D vect1 = new Vector3D();
		Vector3D vect2 = new Vector3D(1, 1, 1);
		Vector3D vect3 = new Vector3D(14.1, -9.7, 32.5);
		Vector3D vect4 = new Vector3D(5, -9, 22);
		assertEquals(0.0, vect1.getModule(), 0.00001);
		assertEquals(1.7320508075688772, vect2.getModule(), 0.00001);
		assertEquals(36.7307772855408, vect3.getModule(), 0.00001);
		assertEquals(24.289915602982237, vect4.getModule(), 0.00001);
		assertEquals(vect4.getScalarProduct(vect4), vect4.getModule()*vect4.getModule(), 0.00001);
		assertEquals(vect3.getScalarProduct(vect3), vect3.getModule()*vect3.getModule(), 0.00001);
	}
}
