package hometasks.Fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	public static final int ITERATIONS = 300;
	private volatile boolean renewed = false;
	private volatile List<BigInteger> numbers;
	
	public Fibonacci() {
		this.numbers = new ArrayList<>(ITERATIONS);
		numbers.add(BigInteger.ZERO);
		numbers.add(BigInteger.ONE);
	}
	
	public Fibonacci(List<BigInteger> numbers) {
		this.numbers = numbers;
	}

	public synchronized void generate() {
		BigInteger current;
		BigInteger previous1 = BigInteger.ZERO;
		BigInteger previous2 = BigInteger.ONE;
		int i = 1;
		while ((!renewed) && (i <= ITERATIONS)) {
			current = previous1.add(previous2);
			previous1 = previous2;
			previous2 = current;
			System.out.println("Iteration "+ i + ". Putting number #" + i + ": " + current);
			numbers.add(current);
			renewed = true;
			i++;
			notifyAll();
			while (renewed) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			//Thread.yield();
		}
		System.out.println("Genetator. Done");
		renewed = true;
		notifyAll();
	}
	
	public synchronized void print() {
		int counter = 0;
		try {
			while (! renewed) {
				wait();
				counter++;
				if (counter < numbers.size()-1) {
					System.out.println("Printing number #" + counter + ": " + numbers.get(numbers.size()-1));
					renewed = false;
				}
				notifyAll();
			}
			System.out.println("Printer. Done.");
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		Fibonacci fibNumbers = new Fibonacci();
		Thread generator = new Thread(new FibonacciGenerator(fibNumbers));
		Thread printer = new Thread(new FibonacciPrinter(fibNumbers));
		/*generator.setPriority(1);
		printer.setPriority(10);*/
		
		printer.start();
		generator.start();

	}

}
