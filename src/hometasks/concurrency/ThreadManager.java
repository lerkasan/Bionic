package hometasks.concurrency;

public class ThreadManager {
	private static Thread[] threads = new Thread[2];
	
	public ThreadManager() {
	}
	
	public static Thread[] getThreads() {
		return threads;
	}
	
	public static void setThreads(Thread thread1, Thread thread2) {
		threads[0] = thread1;
		threads[1] = thread2;
	}

	public static void main(String[] args) {
		Runnable myRunnable1 = new Thread3();
		Runnable myRunnable2 = new Thread2();
		Thread myThread1 = new Thread(myRunnable1);
		Thread myThread2 = new Thread(myRunnable2);
		ThreadManager.setThreads(myThread1, myThread2);
		myThread1.start();
		myThread2.start();
	}

}
