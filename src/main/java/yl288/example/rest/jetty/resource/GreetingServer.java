package yl288.example.rest.jetty.resource;

import java.time.ZonedDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("greet")
public class GreetingServer {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingServer.class);

	@GET
	@Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
	public String getGreeting() {
		return "Hello world " + ZonedDateTime.now();
	}

	@POST
	@Path("message")
	public void receiveGreeting() {
		logger.info("Received greeting from page");
	}
}
