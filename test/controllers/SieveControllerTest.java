package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class SieveControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testGenerate() {
		int start = 0;
		int end = 1000;
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/sieve?type=erato&start="+start+"&end="+end);
        Result result = route(app, request);
		System.out.println(result.status() + " " );
        assertEquals(OK, result.status());

	}

}
