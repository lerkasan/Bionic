package hometasks.Factorial;

import java.math.BigInteger;
import hometasks.Exceptions.*;

public class Factorial {
	
	private Factorial() {
	}
	
	static long getLongFactorial (int n) throws WrongArgumentException {
		long fact = 1;
		if (n < 0) {
			throw new WrongArgumentException("n argument for n! can't be a negative number.");
		}
		for (int i=1; i<=n; i++) {
			fact *= i;
		}
		return fact;
	}
	
	static BigInteger getBigIntegerFactorial (int n) throws WrongArgumentException {
		BigInteger fact = BigInteger.valueOf(1);
		if (n < 0) {
			throw new WrongArgumentException("n argument for n! can't be a negative number.");
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
