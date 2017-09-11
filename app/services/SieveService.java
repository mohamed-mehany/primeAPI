package services;

import java.util.ArrayList;

import models.EratoSieve;
import models.MillerRabinSieve;
import models.ParallelMillerRabinSieve;
import models.ParallelSieve;
import models.Sieve;
import org.apache.commons.cli.*;
import org.apache.commons.cli.DefaultParser;
public class SieveService {
	Sieve s;
	public SieveService(int start, int end, String type){
		switch(type){
			case "erato":
				s = new EratoSieve(start, end);
				break;
			case "parallel":
				s = new ParallelSieve(start, end);
				break;
			case "miller":
				s = new MillerRabinSieve(start, end);
				break;
			case "parallelMiller":
				s = new ParallelMillerRabinSieve(start, end);
				break;
		}
	}
	public ArrayList<Integer> generate(){
		ArrayList<Integer> primes = s.generate();
		return primes;
	}
	public static void main(String[] args){
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		CommandLine cmd; 
		options.addOption("s", true, "Range start, default: 1");
		options.addOption("e", true, "Range end, default: 100");
		options.addOption("t", true, "Type of sieve: [erato, parallel, miller, parallelMiller], default: erato");
		try{
			cmd = parser.parse(options, args);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
		int start = 1, end = 100;
		String type = "erato";

		if(cmd.hasOption("s")) {
			start = Integer.parseInt(cmd.getOptionValue("s"));
		}
		if(cmd.hasOption("e")) {
			end = Integer.parseInt(cmd.getOptionValue("e"));
		}
		if(cmd.hasOption("t")) {
			type = cmd.getOptionValue("t");
		}
		SieveService sieve = new SieveService(start, end, type);
		ArrayList<Integer> primes = sieve.generate();
		System.out.println("Primes greater than " + start + " and less than " + end + ": " + primes.toString());
	}

}
