package hometasks.Fibonacci;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FibNumbersGenerator implements Runnable {
	public static final int ITERATIONS = 300;
	private BlockingQueue<BigInteger> queue;
	
	public FibNumbersGenerator() {
		try {
			queue = new ArrayBlockingQueue<>(ITERATIONS);
			queue.put(BigInteger.ZERO);
			queue.put(BigInteger.ONE);
		} catch (InterruptedException e) {
		}
	}
	
	public FibNumbersGenerator(BlockingQueue<BigInteger> queue) {
		try {
			this.queue = queue;
			queue.put(BigInteger.ZERO);
			queue.put(BigInteger.ONE);
		} catch (InterruptedException e) {
		}
	}

	public BlockingQueue<BigInteger> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			BigInteger current;
			BigInteger previous1 = BigInteger.ZERO;
			BigInteger previous2 = BigInteger.ONE;
			for (int i = 1; i <= ITERATIONS; i++) {
				current = previous1.add(previous2);
				previous1 = previous2;
				previous2 = current;
				System.out.println("Iteration "+ i + ". Putting number #" + (i+2) + ": " + current);
				queue.put(current);
			}
			queue.put(BigInteger.valueOf(-1));
			System.out.println("Genetator. Done");
		}  catch (InterruptedException e) {
		}
	}
}
