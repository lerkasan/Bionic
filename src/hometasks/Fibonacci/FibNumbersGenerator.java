package hometasks.Fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersGenerator implements Runnable {
	public static final int ITERATIONS = 30;
	private BlockingQueue<Integer> queue;
	
	public FibNumbersGenerator() {
		try {
			queue = new ArrayBlockingQueue<>(ITERATIONS);
			queue.put(0);
			queue.put(1);
		} catch (InterruptedException e) {
		}
	}
	
	public FibNumbersGenerator(BlockingQueue<Integer> queue) {
		try {
			this.queue = queue;
			queue.put(0);
			queue.put(1);
		} catch (InterruptedException e) {
		}
	}

	public BlockingQueue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			int current;
			int previous1 = 0;
			int previous2 = 1;
			for (int i = 1; i <= ITERATIONS; i++) {
				current = previous1 + previous2;
				previous1 = previous2;
				previous2 = current;
				System.out.println("Iteration "+ i + ". Putting number " + current);
				queue.put(current);
			}
			queue.put(-1);
			System.out.println("Genetator. Done");
		}  catch (InterruptedException e) {
		}
	}
}
