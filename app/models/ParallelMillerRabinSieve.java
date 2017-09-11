package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.Sieve;

public class ParallelMillerRabinSieve extends Sieve {

	public ParallelMillerRabinSieve(int start, int end){
		super(start, end);
	}
	public ArrayList<Integer> generate(){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int nThreads = Runtime.getRuntime().availableProcessors();
		int start = this.getStart(), end = this.getEnd() + 1, rangeLength = (int) Math.ceil((end - start) / nThreads);
		if(rangeLength == 0) {  // Only happens when whole range is less than numbers of threads
			rangeLength++;
		}
		final ArrayList<Callable<ArrayList<Integer>>> ranges = new ArrayList<Callable<ArrayList<Integer>>>();
		final ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		for(int i = start; i <= end; i += rangeLength){
			final int rangeStart = i, rangeEnd = i + rangeLength <= end ? i + rangeLength : end;
			ranges.add(new Callable<ArrayList<Integer>>() { 
				public ArrayList<Integer> call() {
					return primesRange(rangeStart, rangeEnd);
				}        
			});
		}
		try{
			final List<Future<ArrayList<Integer>>> solutions = threadPool.invokeAll(ranges);
			threadPool.shutdown();
			for(final Future<ArrayList<Integer>> solution: solutions){
				primes.addAll(solution.get());	
			}
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
		Collections.sort(primes);
		return primes;
	}
	public ArrayList<Integer> primesRange(int start, int end){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = start; i < end; ++i){
			if(isProbablePrime(i, 20)){
				primes.add(i);
			}
		}
		return primes;
	}
	// Primality test with error probability less than 4 ^ (-precision)
	public boolean isProbablePrime(int n, int precision) {
		if (n == 2 || n == 3){
			return true;
		}
		if (n % 2 == 0 || n < 2){
			return false;
		}
		// Find d, such that (n - 1) =  2^x * d
		long d = n - 1;
		while (d % 2 == 0) {
			d /= 2;
		}
		Random r = new Random();
		while(precision-- > 0){
			long a = Math.abs(r.nextLong()) % (n - 1) + 1, temp = d;
			long mod = modPow(a, n, temp);
			while(temp != n - 1 && mod != 1 && mod != n - 1){
				mod = mulMod(mod, mod, n);
				temp *= 2;
			}
			if(mod != n - 1 && temp % 2 == 0){
				return false;
			}
		}
		return true;
	}
	// Computes (a ^ n ) mod mod in logarithmic time
	public long modPow(long a, long mod, long n) {
		long solution = 1;
		while(n > 0){
			if(n % 2 == 1){
				solution = (solution * a) % mod;
			}
			n = n >> 1;
			a = (a * a) % mod;
		}
		return solution;
	}
	// Computes (a * b) mod mod, even if a * b overflows long
	public long mulMod(long a, long b, long mod){
		long x = 0, y = a % mod;
		while(b > 0){
			if(b % 2 == 1){
				x = (x + y) % mod;
			}
			y = (y * 2) % mod;
			b /= 2;
		}
		return x % mod;
	}

}
