package hometasks.Fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersPrinter implements Runnable {
	private BlockingQueue<Integer> queue;
	
	public FibNumbersPrinter() {
		queue = new ArrayBlockingQueue<>(FibNumbersGenerator.ITERATIONS);
	}

	public FibNumbersPrinter(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public BlockingQueue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		boolean end = false;
		int fib;
		try {
			while (!end) {
				fib = queue.take();
				if (fib != -1) {
					System.out.println("Printing number " + fib);
				} else {
					end = true;
				}
			}
			System.out.println("Printer. Done");
		} catch (InterruptedException e) {
		}
	}

}
