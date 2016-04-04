package hometasks.concurrency;

public class Thread1 extends Thread {
	public static final int ITERATIONS = 100;
	
	public Thread1() {
		super();
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= ITERATIONS; i++) {
				System.out.println("Thread 1. Iteration " + i);
				Thread.sleep(400);
			}
			System.out.println("Thread 1 ended running.");
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		Thread1 myThread1 = new Thread1();
		Runnable myRunnable = new Thread2();
		Thread myThread2 = new Thread(myRunnable);
		myThread1.start();
		myThread2.start();
	}

}
