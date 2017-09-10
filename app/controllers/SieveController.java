package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

import play.libs.Json;

import models.EratoSieve;
import models.MillerRabinSieve;
import models.ParallelSieve;
import models.Sieve;
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
		ObjectNode json = Json.newObject();
		if("erato".equals(type)){
			s = new EratoSieve(start, end);
		}
		else if("parallel".equals(type)){
			s = new ParallelSieve(start, end);
		}
		else if("miller".equals(type)){
			s = new MillerRabinSieve(start, end);
		}
		ArrayList<Integer> primes = s.generate();
		json.put("count", primes.size());
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode array = mapper.valueToTree(primes);
		json.putArray("primes").addAll(array);
		return ok(json); 
	}

}
