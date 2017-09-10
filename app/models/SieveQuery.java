package models;

import java.sql.Timestamp;
import play.db.*;
import io.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class SieveQuery extends Model {
	private int primesCount, start, end;
	private long elapsedTime, createdAt;
	private String sieveType;
	public SieveQuery(int start, int end, int primesCount, long elapsedTime, String sieveType, long createdAt){
		this.start = start;
		this.end = end;
		this.primesCount = primesCount;
		this.elapsedTime = elapsedTime;
		this.sieveType = sieveType;
		this.createdAt = createdAt;
	}
	public static Finder<String, SieveQuery> find = new Finder<>(SieveQuery.class);
	public static void create(SieveQuery q){
		q.save();
	}
	public String toString(){
		return "Start: " + start + ", End: " + end + ", Primes Count: " + primesCount + ", elapsedTime: " + elapsedTime + " Sieve Type: " + sieveType;
	}

	public int getPrimesCount() {
		return primesCount;
	}

	public void setPrimesCount(int primesCount) {
		this.primesCount = primesCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getSieveType() {
		return sieveType;
	}

	public void setSieveType(String sieveType) {
		this.sieveType = sieveType;
	}

	public static Finder<String,SieveQuery> getFind() {
		return find;
	}

	public static void setFind(Finder<String,SieveQuery> find) {
		SieveQuery.find = find;
	}
}
