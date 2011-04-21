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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.lvl.au.exception.AppException;
import com.lvl.au.exception.BadParameterException;
import com.lvl.au.pojo.EnumStatus;
import com.lvl.au.pojo.IntegerItems;
import com.lvl.au.pojo.World;
import com.lvl.au.pojo.wrapper.IntegerWrapper;
import com.lvl.au.service.WorldService;

/**
 * The resource is created for each request.
 */
@Path("/world")
public class WorldResource {

	private WorldService service = new WorldService();

	@GET
	@Produces("text/plain")
	public String doGet() {
		System.out.println("doGet");
		return "hello";
	}


	@GET
	@Path("/{wid}")
	@Produces({"application/json", "text/xml"})
	public World getWorld(@PathParam("wid") final Integer worldId) {
		System.out.println("getWorld " + worldId);
		return service.getWorld(worldId);
	}

	@HEAD
	@Path("/{wid}")
	@Produces({"application/json", "text/xml"})
	public void headWorld(@PathParam("wid") final Integer worldId) {
		System.out.println("headWorld " + worldId);
		service.getWorld(worldId);
	}

//	@POST
//	@Produces({"application/json", "text/xml"})
//	public World postWorld(World world) {
//		System.out.println("postWorld " + world);
//		World newWorld = service.postWorld(world);
//		System.out.println("postWorld out " + world);
//		return newWorld;
//	}
	@POST
	@Produces({"application/json", "text/xml"})
	public Response postWorld(World world, @Context UriInfo urii) {
		System.out.println("postWorld " + world);
		World newWorld = service.postWorld(world);
		System.out.println("postWorld out " + world);
		URI location = urii.getAbsolutePathBuilder()
			.segment(newWorld.getId().toString())
			.build();
		ResponseBuilder builder = Response.created(location);
		builder.entity(newWorld);
		builder.status(Status.OK);

		return builder.build();
	}

	@PUT
	@Path("/{wid}")
	@Produces({"application/json", "text/xml"})
	public World putWorld(@PathParam("wid") Integer id, World world) {
		System.out.println("putWorld " + id);
		World oldWorld = service.getWorld(id);
		oldWorld.setName(world.getName());
		World updWorld = service.putWorld(oldWorld);
		return updWorld;
	}

	@DELETE
	@Path("/{wid}")
	public void deleteWorld(@PathParam("wid") Integer worldId) {
		service.deleteWorld(worldId);
	}

	@GET
	@Path("banzai")
	@Produces({"application/json", "text/xml"})
	public Response banzaiMultiParam(@QueryParam("ids") String idstring) throws AppException {
//		System.out.println( Arrays.deepToString(ids));
		System.out.println(idstring);
		Integer[] ids = toIntegerArray(idstring);
//		GenericEntity<Integer[]> items = new GenericEntity<Integer[]>(ids) {};
//		GenericEntity<List<Integer>> items = new GenericEntity<List<Integer>>(Arrays.asList(ids)) {};
//		List<Integer> items = Arrays.asList(ids);
//		return Response.ok(items).build();
//		return Response.ok(new GenericEntity<Integer[]>(ids) {}).build();
//		return Response.ok(Arrays.deepToString(ids)).build();	// works

//		Items<Integer> items = new Items<Integer>();
//		items.setList(Arrays.asList(ids));
//		return Response.ok(new GenericEntity<Items>(items) {}).build();	// works, ugly

		IntegerItems items = new IntegerItems();
		items.setList(ids);
		return Response.ok(items).build();
	}

	@GET
	@Path("{wid}/int")
	public Response gimmeInt(@PathParam("wid") Integer worldId) {
		return Response.ok(new IntegerWrapper(worldId)).build();
	}

	private Integer[] toIntegerArray(String commaDelimitedIntegers) throws BadParameterException {
		final String rstr = "([0-9]+\\,?)+";
		if (!commaDelimitedIntegers.matches(rstr)) {
			throw new BadParameterException(" Invalid character(s) included - must be 1,2,3,... ");
		}
		String[] nums = commaDelimitedIntegers.split("\\,");
		Integer[] ints = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			ints[i] = Integer.valueOf(nums[i]);
		}
		return ints;
	}

	@GET
	@Path("statii")
	public Response statuses() {
		EnumStatus[] statii = {EnumStatus.GOOD, EnumStatus.UGLY};
		return Response.ok(statii).build();
	}
}
