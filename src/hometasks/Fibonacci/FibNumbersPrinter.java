 package hometasks.Fibonacci;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersPrinter implements Runnable {
	private BlockingQueue<BigInteger> queue;
	
	public FibNumbersPrinter() {
		queue = new ArrayBlockingQueue<>(FibNumbersGenerator.ITERATIONS);
	}

	public FibNumbersPrinter(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public BlockingQueue<BigInteger> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		boolean end = false;
		int iteration = 0;
		BigInteger fib;
		try {
			while (!end) {
				fib = queue.take();
				if (fib != BigInteger.valueOf(-1)) {
					iteration++;
					System.out.println("Printing number #" + iteration + ": " + fib);
				} else {
					end = true;
				}
			}
			System.out.println("Printer. Done");
		} catch (InterruptedException e) {
		}
	}

}
