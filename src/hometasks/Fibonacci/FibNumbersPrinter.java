package hometasks.Fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersPrinter implements Runnable {
	private BlockingQueue<Long> queue;
	
	public FibNumbersPrinter() {
		queue = new ArrayBlockingQueue<>(FibNumbersGenerator.ITERATIONS);
	}

	public FibNumbersPrinter(BlockingQueue<Long> queue) {
		this.queue = queue;
	}

	public BlockingQueue<Long> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Long> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		boolean end = false;
		int iteration = 0;
		long fib;
		try {
			while (!end) {
				fib = queue.take();
				if (fib != -1) {
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
