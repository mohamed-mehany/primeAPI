package models;
import java.util.ArrayList;
import java.util.Random;

import models.Sieve;

public class MillerRabinSieve extends Sieve {

	public MillerRabinSieve(int start, int end){
		super(start, end);
	}
	public ArrayList<Integer> generate(){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int start = this.getStart(), end = this.getEnd();
		for(int i = start; i <= end; ++i){
			if(isProbablePrime(i, 20)){
				primes.add(i);
			}
		}
		return primes;
	}
	public boolean isProbablePrime(int n, int precision) {
		if (n == 2 || n == 3){
			return true;
		}
		if (n % 2 == 0 || n < 2){
			return false;
		}
		// Write (n - 1) as 2^s * d
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
