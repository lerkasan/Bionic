package hometasks.concurrency;

public class Thread2a implements Runnable {
	public static final int ITERATIONS = 50;
	
	public Thread2a() {
	}

	@Override
	public void run() {
		try {
			ExecutorManager.setThread2(Thread.currentThread());
			for (int i = 1; i <= ITERATIONS; i++) {
				System.out.println("Thread 2. Iteration " + i);
				Thread.sleep(1100);
			}
			System.out.println("Thread 2 ended running.");
		} catch (InterruptedException e) {
		}
	}

}
