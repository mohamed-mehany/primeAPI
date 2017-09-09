package models;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.Sieve;

public class ParallelSieve extends Sieve {
	private BitSet prime;
	ArrayList<Integer> initialPrimes;
	public ParallelSieve(int start, int end){
		super(start, end);
		prime = new BitSet(end + 1);
		prime.flip(2, end + 1);
		initialPrimes = new ArrayList<Integer>();
		for (long i = 2; i * i * i * i <= end; i++) {
			if (prime.get((int)i)) {
				for (long j = i * i; j <= end; j += i) {
					prime.clear((int)j);
				}
			} 
		}
		for(int i = 2; i*i < end; ++i){
			if(prime.get(i)){
				initialPrimes.add(i);
			}
		}
	}
	public ArrayList<Integer> generate(){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.addAll(initialPrimes);
		final ArrayList<Callable<ArrayList<Integer>>> ranges = new ArrayList<Callable<ArrayList<Integer>>>();
		int nThreads = Runtime.getRuntime().availableProcessors();
		final ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		int start = this.getStart(), end = this.getEnd() + 1, rangeLength = (int) Math.ceil((end - start) / nThreads);
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

	private ArrayList<Integer> primesRange(int start, int end){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int maxLength = end - start + 1;
		BitSet prime = new BitSet(maxLength);
		prime.set(0, maxLength);  
		for (Integer p: initialPrimes)
		{
			int firstMult = (start / p) * p;
			for (int j = firstMult >= start ? firstMult : firstMult + p ; j < end; j += p){				
				prime.clear(j - start);
			}
		}
		for (int i = start; i < end; i++){
			if (prime.get(i - start)){
				if(i != 1){
					primes.add(i);	
				}
			}
		}
		return primes;
	}
}
