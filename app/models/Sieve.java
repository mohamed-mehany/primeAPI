package models;
import java.util.ArrayList;

public abstract class Sieve{
	private int start, end;
	public Sieve(int start, int end){
		this.start = start;
		this.end = end;
	}
	public int getStart(){
		return start;
	}

	public int getEnd(){
		return end;
	}
	public abstract ArrayList<Integer> generate();
	
}
