package com.lvl.au.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.lvl.au.pojo.Hello;
import com.lvl.au.service.HelloService;

/**
 * The resource is created for each request.
 */
@Path("/hello")
public class HelloResource {

	private HelloService service = new HelloService();

	@GET
	@Produces("text/plain")
	public String doGet() {
		System.out.println("doGet");
		return "hello";
	}


	@GET
	@Path("/{helloid}")
	@Produces({"application/json", "text/xml"})
	public Hello getHello(@PathParam("helloid") final Integer helloId) {
		System.out.println("getHello " + helloId);
		return service.getHello(helloId);
	}

	@HEAD
	@Path("/{helloid}")
	@Produces({"application/json", "text/xml"})
	public void headHello(@PathParam("wid") final Integer helloId) {
		System.out.println("headHello " + helloId);
		service.getHello(helloId);
	}

	@POST
	@Produces({"application/json", "text/xml"})
	public Response postHello(Hello hello, @Context UriInfo urii) {
		System.out.println("postHello " + hello);
		Hello newHello = service.postHello(hello);
		System.out.println("postHello out " + hello);
		URI location = urii.getAbsolutePathBuilder()
			.segment(newHello.getId().toString())
			.build();

		return Response.created(location)
			.entity(newHello)
			.build();
	}

	@PUT
	@Path("/{helloid}")
	@Produces({"application/json", "text/xml"})
	public Hello putHello(@PathParam("helloid") Integer id, Hello hello) {
		System.out.println("putHello " + id);
		Hello oldHello = service.getHello(id);
		oldHello.setGreeting(hello.getGreeting());
		Hello updHello = service.putHello(oldHello);
		return updHello;
	}

	@DELETE
	@Path("/{helloid}")
	public void deleteHello(@PathParam("helloid") Integer helloId) {
		service.deleteHello(helloId);
	}

}
