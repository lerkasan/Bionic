package hometasks.Fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersGenerator implements Runnable {
	public static final int ITERATIONS = 70;
	private BlockingQueue<Long> queue;
	
	public FibNumbersGenerator() {
		try {
			queue = new ArrayBlockingQueue<>(ITERATIONS);
			queue.put(0L);
			queue.put(1L);
		} catch (InterruptedException e) {
		}
	}
	
	public FibNumbersGenerator(BlockingQueue<Long> queue) {
		try {
			this.queue = queue;
			queue.put(0L);
			queue.put(1L);
		} catch (InterruptedException e) {
		}
	}

	public BlockingQueue<Long> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Long> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			long current;
			long previous1 = 0;
			long previous2 = 1;
			for (int i = 1; i <= ITERATIONS; i++) {
				current = previous1 + previous2;
				previous1 = previous2;
				previous2 = current;
				System.out.println("Iteration "+ i + ". Putting number #" + (i+2) + ": " + current);
				queue.put(current);
			}
			queue.put(-1L);
			System.out.println("Genetator. Done");
		}  catch (InterruptedException e) {
		}
	}
}
