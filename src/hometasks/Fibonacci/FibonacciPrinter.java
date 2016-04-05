package hometasks.Fibonacci;

public class FibonacciPrinter implements Runnable {
	private Fibonacci fib;
	
	public FibonacciPrinter() {
		fib = new Fibonacci();
	}
	
	public FibonacciPrinter(Fibonacci fib) {
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
		fib.print();
	}
	
	

}
