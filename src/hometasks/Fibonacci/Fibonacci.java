package hometasks.Fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	public static final int ITERATIONS = 30;
	private boolean renewed = false;
	private volatile List<Integer> numbers;
	
	public Fibonacci() {
		this.numbers = new ArrayList<>(ITERATIONS);
		numbers.add(0);
		numbers.add(1);
	}
	
	public Fibonacci(List<Integer> numbers) {
		this.numbers = numbers;
	}

	synchronized public void generate() {
		int current;
		int previous1 = 0;
		int previous2 = 1;
		for (int i = 1; i <= ITERATIONS; i++) {
			current = previous1 + previous2;
			previous1 = previous2;
			previous2 = current;
			System.out.println("Iteration "+ i + ". Putting number " + current);
			numbers.add(current);
			renewed = true;
			notifyAll();
			Thread.yield();
		}
		System.out.println("Genetator. Done");
	}
	
	synchronized public void print() {
		try {
			while (! renewed) {
				wait();
			}
			System.out.println("Printing number " + numbers.get(numbers.size()-1));
			
		//	count++;
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		Fibonacci fibNumbers = new Fibonacci();
		Thread generator = new Thread(new FibonacciGenerator(fibNumbers));
		Thread printer = new Thread(new FibonacciPrinter(fibNumbers));
		generator.setPriority(1);
		printer.setPriority(10);
		
		printer.start();
		generator.start();

	}

}
