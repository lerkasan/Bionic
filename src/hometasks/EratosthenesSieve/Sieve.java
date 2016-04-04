package hometasks.EratosthenesSieve;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;
import hometasks.Exceptions.WrongArgumentException;

public class Sieve {
	int limit;
	//private List<Integer> primes;
	private Set<Integer> primes;
	
	public Sieve() {
		this.limit = 0; 
		//this.primes = new ArrayList<Integer>();
		this.primes = new ConcurrentSkipListSet<Integer>();
	}
	
	public Sieve(int limit) {
		if (limit < 2) {
			throw new WrongArgumentException("Limit argument must be a natural number greater than 1.");
		}
		this.limit = limit;
		//this.primes = new ArrayList<Integer>(limit-1);
		this.primes = new ConcurrentSkipListSet<Integer>();
		/*for (int i = 2; i <= limit; i++) {
			this.primes.add(i);
		}*/
		IntStream.iterate(2, i -> i+1)
			.limit(limit-1)
			.forEach(this.primes::add);
		//System.out.println(this.primes);
	}
	
	public Set<Integer> getPrimes() {
	//public List<Integer> getPrimes() {
		if ( (this.limit == 2) || (this.limit == 3) ) {
			return this.primes;
		}
		if (this.primes.size() != this.limit-1) {
			return this.primes;
		}
		//List<Integer> primes = new ArrayList<Integer>(this.primes);
		//try {		
			this.primes.stream()
				.filter(Objects::nonNull)
				.filter(divisor -> divisor <= (int)Math.sqrt(this.limit)+1)
				.forEach(divisor -> {
					this.primes.removeIf(numb -> (numb > divisor) && (numb % divisor == 0)); 
					System.out.println("Removing divided by "+divisor+": "+this.primes);
					});
			System.out.println();
		/*}
		catch (ConcurrentModificationException e) {
		}*/
		return this.primes;
	}
	
	public Set<Integer> getPrimes2() {
	//public List<Integer> getPrimes2() {
		for (int divisor=2; divisor <= (int)Math.sqrt(this.limit)+1; divisor++) {
			//for (Iterator<Integer> it = this.primes.iterator(); it.hasNext(); ) {
			for (Iterator<Integer> iter = this.primes.iterator(); iter.hasNext(); ) {
				int numb = iter.next();
				if ((numb > divisor) && (numb % divisor == 0)) {
					iter.remove();
				}
			}
		}
		//	System.out.println("Removing divided by "+divisor+": "+this.primes);
		return this.primes;
	}
	
	public Set<Integer> getPrimes3() {
	//public List<Integer> getPrimes3() {
		for (Iterator<Integer> it = this.primes.iterator(); it.hasNext(); ) {
			int divisor = it.next();
			for (Iterator<Integer> iter = this.primes.iterator(); iter.hasNext(); ) {
				int numb = iter.next();
				if ((numb > divisor) && (numb % divisor == 0)) {
					iter.remove();
				}
			}
		}
		//	System.out.println("Removing divided by "+divisor+": "+this.primes);
		return this.primes;
	}
	
	public static double comparePerformance(int n) {
		double times = 0.0;
		Sieve sieve1 = new Sieve(n);
		Sieve sieve2 = new Sieve(n);
		long before = System.nanoTime();
		sieve1.getPrimes();
		long after = System.nanoTime();
		long delta1 = after - before;
		// System.out.println(text1);
		System.out.println("\n" + n + " numbers:");
		System.out.println("Stream execution time: "
				+ (double) ((int) Math.round(1000000 * (delta1 * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ delta1 + " nanoseconds");
		before = System.nanoTime();
		sieve2.getPrimes2();
		after = System.nanoTime();
		long delta2 = after - before;
		// System.out.println(text2);
		System.out.println("Cycle execution time: "
				+ (double) ((int) Math.round(1000000 * (delta2 * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ delta2 + " nanoseconds");
		if (delta1 == delta2) {
			System.out.println("performance is equal.");
		}
		if (delta1 > delta2) {
			double temp1 = (delta1 * 1.0) / delta2;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("Cycle is quicker in " + times + " times.");
		} else {
			double temp1 = (delta2 * 1.0) / delta1;
			int temp2 = (int) Math.round(temp1 * 1000);
			times = (double) temp2 / 1000;
			System.out.println("Stream is quicker in " + times + " times.");
		}

		return times;
	}

	public static void main(String[] args) {
		System.out.println("Result - stream implementation: " + new Sieve(1520).getPrimes());
		/*System.out.println("Result - cycle implementation: " + new Sieve(1510).getPrimes2());
		Sieve sieve1 = new Sieve(1000000);
		long before = System.nanoTime();
		sieve1.getPrimes();
		long after = System.nanoTime();
		long delta1 = after - before;
		System.out.println("Stream execution time for 1000000 numbers: "
				+ (double) ((int) Math.round(1000000 * (delta1 * 1.0) / 1000000000)) / 1000000 + " seconds or "
				+ delta1 + " nanoseconds");
		
		//System.out.println("\nResult: " + sieve1.getPrimes());
		for (int i = 10; i <=100000; i *= 10) {
			Sieve.comparePerformance(i);
		} 
		//System.out.println("Result: " + new Sieve(1000000).getPrimes());*/
	}

}
