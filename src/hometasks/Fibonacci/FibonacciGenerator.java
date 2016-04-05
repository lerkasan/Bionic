package hometasks.Fibonacci;

public class FibonacciGenerator implements Runnable {
	private Fibonacci fib;
	
	public FibonacciGenerator() {
		fib = new Fibonacci();
	}

	public FibonacciGenerator(Fibonacci fib) {
		this.fib = fib;
	}
	
	public Fibonacci getFib() {
		return fib;
	}

	public void setFib(Fibonacci fib) {
		this.fib = fib;
	}

	@Override
	public void run() {
		fib.generate();
	}
	
	

}
