package hometasks.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManager {
	private static Thread[] threads = new Thread[2];
	
	public ExecutorManager() {
	}
	
	public static Thread[] getThreads() {
		return threads;
	}
	
	public static void setThread1(Thread thread) {
		threads[0] = thread;
	}
	
	public static void setThread2(Thread thread) {
		threads[1] = thread;
	}

	public static void main(String[] args) {
		Runnable myRunnable1 = new Thread1a();
		Runnable myRunnable2 = new Thread2a();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.execute(myRunnable1);
		pool.execute(myRunnable2);
		pool.shutdown();
	}

}
