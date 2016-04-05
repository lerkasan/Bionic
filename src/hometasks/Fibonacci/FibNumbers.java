package hometasks.Fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbers {

	public static void main(String[] args) {
		System.out.println("Fibonacci numbers:");
		BlockingQueue<Long> queue = new ArrayBlockingQueue<>(FibNumbersGenerator.ITERATIONS);
		Thread generator = new Thread(new FibNumbersGenerator(queue));
		Thread printer = new Thread(new FibNumbersPrinter(queue));
		generator.start();
		printer.start();
	}
}
