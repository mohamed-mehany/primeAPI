import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import play.http.HttpErrorHandler;

import play.mvc.*;
import play.mvc.Http.*;
import play.libs.Json.*;
import play.libs.Json;
@Singleton
public class ErrorHandler implements HttpErrorHandler {
    public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
		ObjectNode json = Json.newObject();
		json.put("message", "A client error occurred: " + message);
        return CompletableFuture.completedFuture(
                Results.status(statusCode, json)
        );
    }

    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
		ObjectNode json = Json.newObject();
		json.put("message", "A server error occured: " + exception.getMessage());
        return CompletableFuture.completedFuture(
                Results.internalServerError(json)
        );
    }
}
