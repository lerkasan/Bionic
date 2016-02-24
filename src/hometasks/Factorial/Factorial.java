package hometasks.Factorial;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factorial {
	
	private Factorial() {
	}
	
	static long getLongFactorial (int n) throws IllegalArgumentException {
		long fact = 1;
		if (n < 0) {
			IllegalArgumentException  e = new IllegalArgumentException("n argument for n! can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
		for (int i=1; i<=n; i++) {
			fact *= i;
		}
		return fact;
	}
	
	static BigInteger getBigIntegerFactorial (int n) throws IllegalArgumentException {
		BigInteger fact = BigInteger.valueOf(1);
		if (n < 0) {
			IllegalArgumentException  e = new IllegalArgumentException("n argument for n! can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
		for (int i=1; i<=n; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		return fact;
	}

	public static void main(String[] args) {
		System.out.println("Number\t\t long factorial\t\t\t\t\t\t BigIntegerFactorial");
		for (int i=1; i<=25; i++) {
			System.out.println(i + "\t\t"+Factorial.getLongFactorial(i)+ "\t\t\t\t\t\t "+Factorial.getBigIntegerFactorial(i));
		}
		//System.out.println("long 25! = " + Factorial.getLongFactorial(25));
		//System.out.println("BigInteger 25! = " + Factorial.getBigIntegerFactorial(25));
	}
}
