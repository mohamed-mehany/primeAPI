package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import play.libs.Json;

import models.EratoSieve;
import models.MillerRabinSieve;
import models.ParallelMillerRabinSieve;
import models.ParallelSieve;
import models.Sieve;
import models.SieveQuery;
import play.libs.Json.*;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the SieveController and connects to the sieve model.
 */
public class SieveController extends Controller {
	Sieve s;
	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public Result generate(String type, int start, int end){
		long startTime = System.currentTimeMillis();
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
		ArrayList<Integer> primes = s.generate();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;		
		int primesCount = primes.size();
		SieveQuery result = new SieveQuery(start, end, primesCount, elapsedTime, type, Instant.now().getEpochSecond());
		SieveQuery.create(result);
		ObjectNode json = Json.newObject();
		json.put("count", primesCount);
		json.put("elapsedTime", elapsedTime);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode array = mapper.valueToTree(primes);
		//json.putArray("primes").addAll(array);
		return ok(json); 
	}
	public Result getQueries(){
		List<SieveQuery> results = SieveQuery.find.all();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = Json.newObject(); 
		ArrayNode array = mapper.valueToTree(results);
		json.putArray("results").addAll(array);
		return ok(json);
	}

}
