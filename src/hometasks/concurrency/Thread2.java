package hometasks.concurrency;

public class Thread2 implements Runnable {
	public static final int ITERATIONS = 50;
	
	public Thread2() {
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= ITERATIONS; i++) {
				System.out.println("Thread 2. Iteration " + i);
				Thread.sleep(1100);
			}
			System.out.println("Thread 2 ended running.");
		} catch (InterruptedException e) {
		}
	}

}
