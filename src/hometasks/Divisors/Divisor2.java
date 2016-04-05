package hometasks.Divisors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Divisor2 implements Runnable {
	public static final int THREADSAMOUNT = 31;
	private long number;
	private List<FutureTask<Long>> parts; 
	ExecutorService pool = Executors.newCachedThreadPool();
	
	private class Partition implements Callable<Long> {
		private long from;
		private long to;
		
		private Partition() {
		}
		
		private Partition(long from, long to) {
			this.from = from;
			this.to = to;
		}
		
		@Override
		public Long call() {
			long amount = 0;
			//System.out.println("Starting Callable");
			for (long i = from; i <= to; i++) {
				if (number % i == 0) {
					amount++;
					System.out.print(i+" ");
					if (i * i != number) {
						amount++;
						System.out.print(number/i+" ");
					}
				}

			}
			//System.out.println("Finishing Callable");
			return amount;
		}
	}
	
	public Divisor2() {
		parts = new ArrayList<>(THREADSAMOUNT);
	}
	
	public Divisor2(long number) {
		this.number = number;
		parts = new ArrayList<>(THREADSAMOUNT);
	}
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public void run() {
		try {
			long from = 0;
			long to = 0;
			long totalAmount = 0;
			int index = 0;
			int max = (int)Math.sqrt(number)+1;
			System.out.print("Divisors of number " + number + ": ");
			for (int i = 1; i < max; i += max/THREADSAMOUNT) {
				index++;
				from = i;
				to = (long)(i + max/THREADSAMOUNT - 1);
				System.out.println("New interval (" + from + ", " + to + ")");
				Partition partitioning = new Partition(from,to);
				FutureTask<Long> task = new FutureTask<>(partitioning);
				parts.add(task);
				System.out.println("\nStarting thread #" + index);
				pool.execute(task);
				System.out.println("\nFinishing thread #" + index);
			}
			for (FutureTask<Long> part : parts) {
				totalAmount += part.get();
			}
			pool.shutdown();
			System.out.println("\nNumber " + number + " has " + totalAmount + " Divisors.");
		} catch (InterruptedException | ExecutionException e) {
		}	
	}
	
	public static void main(String[] args) {
		Divisor2 divide1 = new Divisor2(4057884692476320L);
		Thread thread1 = new Thread(divide1);
		thread1.start();
	}
}
