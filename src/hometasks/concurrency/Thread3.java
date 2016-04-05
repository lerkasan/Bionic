package hometasks.concurrency;

public class Thread3 extends Thread {
	public static final int ITERATIONS = 100;
	
	public Thread3() {
		super();
	}
	
	@Override
	public void run() {
		try {
			for (int i = 1; i <= ITERATIONS; i++) {
				System.out.println("Thread 1. Iteration " + i);
				Thread.sleep(400);
				if (i == ITERATIONS/2) {
					System.out.println("Thread 1. Waiting for Thread 2");
					ThreadManager.getThreads()[1].join();
				}
			}
			System.out.println("Thread 1 ended running.");
		} catch (InterruptedException e) {
		}
	}

}
