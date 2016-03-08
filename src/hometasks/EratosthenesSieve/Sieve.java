package EratosthenesSieve;

import java.util.ArrayList;

import hometasks.Exceptions.WrongArgumentException;

public class Sieve {
	int limit;
	private ArrayList<Integer> primes;
	
	public Sieve() {
		this.limit = 0;
		this.primes = new ArrayList<Integer>();
	}
	
	public Sieve(int limit) {
		if (limit < 2) {
			throw new WrongArgumentException("Limit argument must be a natural number greater than 1.");
		}
		this.limit = limit;
		this.primes = new ArrayList<Integer>(limit);
		for (int i = 2; i <= limit; i++) {
			this.primes.add(i);
		}
	}
	
	public ArrayList<Integer> getPrimes() {
		if ( (this.limit == 2) || (this.limit == 3) ) {
			return this.primes;
		}
		if (this.primes.size() != this.limit-1) {
			return this.primes;
		}
		ArrayList<Integer> primes = new ArrayList<Integer>(this.primes);
				
		primes.stream()
			.filter(divisor -> divisor <= Math.sqrt(this.limit)+1)
			.forEach(divisor -> this.primes.removeIf(numb -> (numb > divisor) && (numb % divisor == 0)));
		
		return this.primes;
	}

	public static void main(String[] args) {
		System.out.println(new Sieve(100000).getPrimes());
	}

}
