
package controllers;

import play.mvc.*;
import play.libs.Json;
import play.libs.Json.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This controller contains an action to handle HTTP requests
 * to the SieveController and connects to the sieve model.
 */
public class SieveController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	public Result generate(String type, int start, int end){
		JsonNode json = Json.parse("{\"count\":\"0\", \"lastName\":\"Bar\", \"age\":13}");
		return ok(json); 
	}

}
