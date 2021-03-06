package hometasks.NaturalNumber;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import hometasks.Exceptions.*;

/** represents natural number and its prime and common divisors*/
public class NaturalNumber {
	/** natural number*/
	private long num;
	/** array of prime divisors of natural number*/
	private long[] primeDivs;
	/** array of all divisors of natural number*/
	private long[] allDivs;

	/** default constructor that creates natural number object with a value of 1*/
	public NaturalNumber() {
		this.num = 1;
		this.primeDivs = null;
		this.allDivs = null;
	}

	/** constructor that creates natural number object with a value of argument n. 
	 * @throws WrongArgumentException if argument n isn't a natural number.
	 * @param n - natural number with value starting from 1*/
	public NaturalNumber(long n) throws WrongArgumentException {
		if (n > 0) {
			this.num = n;
			this.primeDivs = null;
			this.allDivs = null;
		} else {
			//System.err.println("Constructor received not a positive number");
			throw new WrongArgumentException("Constructor received not a positive number");
		}
	}
	
	/** constructor that creates natural number object as a separate copy of argument numb
	 * @param numb - natural number object to be copied  */
	public NaturalNumber(NaturalNumber numb) {
		this.num = numb.getNum();
		this.allDivs = Arrays.copyOf(numb.getAllDivs(), numb.getAllDivs().length);
		this.primeDivs = Arrays.copyOf(numb.getPrimeDivs(), numb.getPrimeDivs().length);
	}
	
	/** creates natural number object as a separate copy of current object*/
	public NaturalNumber copy() {
		return new NaturalNumber(this);
	}
	
	/** creates natural number object as a separate copy of argument numb
	 * @param numb - natural number object to be copied  */
	public static NaturalNumber copy(NaturalNumber numb) {
		return new NaturalNumber(numb);
	}
	
	/** checks if natural number object equals argument numb object.
	 * @param numb - natural number object to check equality with
	 * @return true if natural number objects are equal else false*/
	@Override
	public boolean equals(Object numb) {  // TO DO - override hashCode() also!
        if (this == numb) {
        	return true;
        }
        if (numb == null) {
        	return false;
        }
        if (this.getClass() != numb.getClass()) {
        	return false;
        }
        NaturalNumber numbObj = (NaturalNumber)numb;
		boolean equal = (this.num == numbObj.getNum()) 
				&& (Arrays.equals(this.getAllDivs(), numbObj.getAllDivs())) 
				&& (Arrays.equals(this.getPrimeDivs(), numbObj.getPrimeDivs()));
		return equal;
	}

	/** return value of natural number object */ 
	public long getNum() {
		return this.num;
	}

	/** sets field num of natural number object with a value of argument n. 
	 * @throws WrongArgumentException if argument n isn't a natural number.
	 * @param n - natural number with value starting from 1*/
	public void setNum(long n) throws WrongArgumentException {
		if (n > 0) {
			this.num = n;
			this.primeDivs = null;
			this.allDivs = null;
		} else {
			System.err.println("setNum method received not a positive number");
			throw new WrongArgumentException("setNum method received not a positive number");
		}
	}

	// ------------------------ Calculating Divisors --------------------------------

	/** calculates all divisors of current natural number.
	 * @return array consisting of all divisors of current natural number*/
	public long[] getAllDivs() {
		if (this.allDivs != null) {
			return this.allDivs;
		}
		int allDivsAmount = 0;
		long[] tempAllDivs;
		long sqrtN = (int) Math.sqrt(this.num);
		tempAllDivs = new long[(int) (this.num / 2 + 1)];
		for (long i = 1; i <= sqrtN; i++) {
			if (this.num % i == 0) {
				tempAllDivs[allDivsAmount] = i;
				if (allDivsAmount <= this.num / 2) {
					allDivsAmount++;
				}
				if (i * i != this.num) {
					tempAllDivs[allDivsAmount] = this.num / i;
					if (allDivsAmount <= this.num / 2) {
						allDivsAmount++;
					}
				}
			}
		}
		this.allDivs = new long[allDivsAmount];
		for (int i = 0; i < allDivsAmount; i++) {
			this.allDivs[i] = tempAllDivs[i];
		}
		return this.allDivs;
	}

	/** verifies if current natural number is prime
	 * @return true if natural number is prime else false*/
	public boolean isPrime() {
		return this.getAllDivs().length == 2;
	}

	/** calculates prime divisors of current natural number.
	 * @return array consisting of prime divisors of current natural number*/
	public long[] getPrimeDivs() {
		if (this.primeDivs != null) {
			return this.primeDivs;
		}
		int primeDivsAmount = 0;
		long[] tempPrimeDivs = new long[(int) (this.num / 2 + 1)];
		for (long i : this.getAllDivs()) {
			NaturalNumber component = new NaturalNumber(i);
			if (component.isPrime()) {
				tempPrimeDivs[primeDivsAmount] = component.getNum();
				if (primeDivsAmount <= component.getNum() / 2) {
					primeDivsAmount++;
				}
			}
		}
		this.primeDivs = new long[primeDivsAmount];
		for (int i = 0; i < primeDivsAmount; i++) {
			this.primeDivs[i] = tempPrimeDivs[i];
		}
		return this.primeDivs;
	}

	// ------------------------ Finding Perfect Numbers --------------------------------

	/** verifies if current natural number is perfect
	 * @return true if natural number is perfect else false*/
	public boolean isPerfect() {
		long sum = 1;
		long[] tempAllDivs = this.getAllDivs();
		for (int k = 2; k < tempAllDivs.length; k++) {
			sum += tempAllDivs[k];
		}
		return (this.num != 1) && (this.num == sum);
	}

	/** calculates first perfect numbers. Amount of numbers is set by argument amount. Amount should be less than 6.
	 * @return array of perfect numbers*/
	public static long[] findPerfects(int amount) { //long[] - only for first 5 number, for the 6th - should be long, for others - BigInteger
		int k = 0;
		long i = 1;
		long test;
		long[] perfects = new long[amount];
		if (amount <= 5) {
			System.out.println("Finding perfect numbers:");
			do {
				test = (int) Math.pow(2, i) - 1;
				NaturalNumber numb = new NaturalNumber(test);
				if (numb.isPrime()) {
					perfects[k] = test * (int) Math.pow(2, i - 1);
					k++;
				}
				i++;
			} while (k < amount);
		} else {
			System.out.println("Not yet implemented for amount greater than 5.");
		}
		return perfects;
	}

	
	/** prints first perfect numbers calculated by brute force starting from start. Amount of numbers is set by argument amount. Amount should be less than 6.
	 * @deprecated*/
	public static void findBruteForcePerfects(long start, long amount) {
		long k = 0;
		long i = start;
		System.out.println("Brute force finding perfect numbers:");
		do {
			NaturalNumber div = new NaturalNumber(i);
			if (div.isPerfect()) {
				System.out.println(i + " is perfect");
				k++;
			}
			i++;
		} while (k < amount);
	}

	/** calculates first perfect numbers. Amount of numbers is set by argument amount. Amount should be less than 6.*/
	public static void printPerfects(int amount) {
		long[] perfects = NaturalNumber.findPerfects(amount);
		for (int i = 0; i < amount; i++) {
			System.out.println(perfects[i] + " is perfect. \tCheck: " + new NaturalNumber(perfects[i]).isPerfect());
		}
	}

	// -------------------------------- Main ----------------------------------

	public static void main(String[] args) {
		boolean letter = false;
		long[] primeDivs;
		long[] allDivs;
		System.out.println("Finding divisors of a natural number. \nPress any letter to exit.");
		System.out.println("Before exit you will see first 5 perfect numbers.");
		Scanner in = new Scanner(System.in);
		do {
			System.out.print("\nInput a number: ");
			if (!in.hasNextLong()) {
				letter = true;
				continue;
			}
			long n = in.nextLong();
			try {
				NaturalNumber numb = new NaturalNumber(n);
				allDivs = numb.getAllDivs();
				primeDivs = numb.getPrimeDivs();
				System.out.println("\nAmount of prime divisors of " + numb.getNum() + " is: " + primeDivs.length);
				System.out.println("Div \tResult");
				for (long k : primeDivs) {
					System.out.println(k + "\t" + numb.getNum() / k);
				}
				System.out.println("\nAmount of all divisors of " + numb.getNum() + " is: " + allDivs.length);
				System.out.println("Div \tResult");
				for (long k : allDivs) {
					System.out.println(k + "\t" + numb.getNum() / k);
				}
				System.out.println("\nIs " + n + " prime? " + numb.isPrime());
				System.out.println("Is " + n + " perfect? " + numb.isPerfect());
			} catch (WrongArgumentException e) {
				Logger logger = Logger.getAnonymousLogger();
				logger.log(Level.SEVERE, "The following exception was thrown:", e);
				System.out.println("You entered not a natural number. Please try again.");
			}
		} while (!letter);
		NaturalNumber.printPerfects(5);
		in.close();
		System.out.print("Bye");
	}
}
