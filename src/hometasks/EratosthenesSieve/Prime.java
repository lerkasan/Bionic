package hometasks.EratosthenesSieve;

import java.util.stream.IntStream;

public class Prime {

	private boolean isPrime(long n) {
	    return n > 1 && IntStream.rangeClosed(2, (int)Math.sqrt(n)).noneMatch(divisor -> n % divisor == 0);
	}

	private long countPrimes(int max) {
	    return IntStream.range(1, max).parallel().filter(this::isPrime).count();
	}

}
