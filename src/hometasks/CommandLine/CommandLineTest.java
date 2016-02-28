package hometasks.CommandLine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.*;

public class CommandLineTest {
	PrintStream old = System.out; // IMPORTANT: Save the old System.out!
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	String[] args1 = {"1", "2", "4", "-5", "83", "0", "-45"};
	String[] args2 = {"0", "9", "-9", "+5"};
	String[] args3 = {"0", "7.8", "-9", "+5", "3"};
	String[] args4 = {"0", "7.8", "abcd", "+5", "3", "-8", "6", "wqwerty"};
	
	@Before
	public void setUp() {
	    PrintStream ps = new PrintStream(baos);
	    old = System.out; // IMPORTANT: Save the old System.out!
	    System.setOut(ps);
	}

	@Test
	public void getArgsSumTest() {
		assertEquals(40,CommandLine.getArgsSum(args1));
		assertEquals(5,CommandLine.getArgsSum(args2));
		assertEquals(-1,CommandLine.getArgsSum(args3));
		assertEquals(6,CommandLine.getArgsSum(args4));
	}
	
	@Test
	public void mainTest() {
		CommandLine.main(args1);
		assertEquals("40", baos.toString());
		baos.reset();
		CommandLine.main(args2);
		assertEquals("5", baos.toString());
		baos.reset();
		CommandLine.main(args3);
		assertEquals("-1", baos.toString());
		baos.reset();
		CommandLine.main(args4);
		assertEquals("6", baos.toString());
		baos.reset();	
	}
	
	@After
	public void tearDown() {
		System.out.flush();
	    System.setOut(old);	   
	}

}
