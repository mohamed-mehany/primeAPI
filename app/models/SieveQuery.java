package models;

import java.sql.Timestamp;
import io.ebean.*;

public class SieveQuery extends Model {
	private int primesCount, start, end;
	private double elapsedTime;
	private String sieveType;
	private Timestamp createdAt;
	public SieveQuery(int start, int end, int primesCount, double elapsedTime, String sieveType, Timestamp createdAt){
		this.start = start;
		this.end = end;
		this.primesCount = primesCount;
		this.elapsedTime = elapsedTime;
		this.sieveType = sieveType;
		this.createdAt = createdAt;
	}
}
