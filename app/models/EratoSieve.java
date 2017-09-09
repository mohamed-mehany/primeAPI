package models;
import java.util.ArrayList;

import java.util.BitSet;
import models.Sieve;

public class EratoSieve extends Sieve {
	private BitSet prime;
	public EratoSieve(int start, int end){
		super(start, end);
		prime = new BitSet(end + 1);
	}
	public ArrayList<Integer> generate(){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int start = this.getStart(), end = this.getEnd();
		prime.flip(2, end + 1);
		for (long i = 2; i <= end; i++) {
			if (prime.get((int)i)) {
				for (long j = i * i; j <= end; j += i) {
					prime.clear((int)j);
				}
				if(i >= start){
					primes.add((int)i);  
				}
			} 
		}
		return primes;
	}

}
