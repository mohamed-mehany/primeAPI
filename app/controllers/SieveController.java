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

import models.SieveQuery;
import play.libs.Json.*;
import play.mvc.*;
import services.SieveService;

/**
 * This controller contains an action to handle HTTP requests
 * to the SieveController and connects to the sieve model.
 */
public class SieveController extends Controller {
	/**
	 * * An action that given the range start, range end and the type of the 
	 * sieve("erato", "parallel", "miller", "parallelMiller"), returns all primes in that range,
	 * their count and elapsed time in milliseconds and saves the query
	 * and its result using the SieveQuery model
	*/
	public Result generate(String type, int start, int end){
		ObjectNode json = Json.newObject();
		if(start > end ){
			json.put("message", "Start can't be greater than end");
			return badRequest(json);
		}
		if(!type.matches("erato|miller|parallel|parallelMiller")){
			json.put("message", "Please select one of the available sieve types: (erato|miller|parallel|parallelMiller)");
			return badRequest(json);
		}
		long startTime = System.currentTimeMillis();
		SieveService sieve = new SieveService(start, end, type);
		ArrayList<Integer> primes = sieve.generate();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;		
		int primesCount = primes.size();
		SieveQuery result = new SieveQuery(start, end, primesCount, elapsedTime, type, Instant.now().getEpochSecond());
		SieveQuery.create(result);
		json.put("count", primesCount);
		json.put("elapsedTime", elapsedTime);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode array = mapper.valueToTree(primes);
		json.putArray("primes").addAll(array);
		return ok(json); 
	}

	/**
	 * An action that returns a list of all queries stored.
	 */
	public Result getQueries(){
		List<SieveQuery> results = SieveQuery.find.all();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = Json.newObject(); 
		ArrayNode array = mapper.valueToTree(results);
		json.putArray("results").addAll(array);
		return ok(json);
	}

}
