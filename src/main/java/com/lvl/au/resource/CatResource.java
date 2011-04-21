// Copyright (c) 2010 - Level Studios
// All rights reserved.
package com.lvl.au.resource;

import static java.lang.System.out;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.lvl.au.exception.NotFoundException;
import com.lvl.au.pojo.Cat;
import com.lvl.au.pojo.Examples;
import com.lvl.au.pojo.wrapper.Stringer;
import com.lvl.au.service.CatService;

/**
 * Explore POE.
 * @author auntiedt
 *
 */
@Path("cat")
public class CatResource {

	@Context UriInfo urii;

	CatService service = new CatService();


	@GET
	@Path("{id}")
	@Produces({"application/json"})
	public Cat get(@PathParam("id") Integer id) throws Exception {
		Cat cat = service.getCat(id);
		if (cat == null) {
			throw new NotFoundException();
		}
		return cat;
	}

	@GET
	@Path("poe")
	public Response getPoe() throws Exception {
		String poeId = service.generatePoeId();
		String path = urii.getAbsolutePath().toString();
		int i = path.lastIndexOf('/');
		path = path.substring(0, i);
		URI location = UriBuilder.fromPath(path)
			.segment(poeId)
			.build();
		return Response.ok(poeId)
			.location(location)
			.header("POE-Links", location)
			.build();
	}

	/**
	 * @MatrixParam does not work with POST.
	 * @param poeId
	 * @request.representation.example {@link Examples#SAMPLE_CAT}
	 * @param cat
	 * @return {@link Cat}
	 * @throws Exception
	 */
	@POST
	@Path("{poeid}")
	public Response add(@PathParam("poeid") String poeId, Cat cat) throws Exception {
		out.println("param poeId=" + poeId);
		out.println("server poeId=" + service.getPoeId());
		if (!service.claimPoeId(poeId)) {
			return Response.status(405)
				.entity("Operation Not Supported: POE id '" + poeId + "' has either been used up or is invalid" )
				.build();
		}
		Cat newCat = service.addCat(cat);
		String path = urii.getAbsolutePath().toString();
		int i = path.lastIndexOf('/');
		path = path.substring(0, i);
		URI location = UriBuilder.fromPath(path)
			.segment(newCat.getId().toString())
			.build();
		return Response.ok(cat)
			.location(location)
			.build();

	}
	@POST
	@Path("{id: id}")
	public Response addMatrix(@PathParam("id") PathSegment pseg, Cat cat) throws Exception {
		MultivaluedMap<String, String> mm = pseg.getMatrixParameters();
		List<String> gaa = mm.get("gaa");
		out.println("param gaa=" + gaa.get(0));
		List<String> poes = mm.get("poeid");
		String poeId = poes.get(0);
		out.println("param poeId=" + poeId);
		out.println("server poeId=" + service.getPoeId());
		if (!service.claimPoeId(poeId)) {
			return Response.status(405)
				.entity("Operation Not Supported: POE id '" + poeId + "' has either been used up or is invalid" )
				.build();
		}
		Cat newCat = service.addCat(cat);
		String path = urii.getAbsolutePath().toString();
		int i = path.lastIndexOf('/');
		path = path.substring(0, i);
		URI location = UriBuilder.fromPath(path)
			.segment(newCat.getId().toString())
			.build();
		return Response.ok(cat)
			.location(location)
			.build();

	}

	@PUT
	@Path("kittens")
	@Consumes({"application/json"})
	public Response addKitten(Stringer values) throws Exception {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < values.getArray().length; i++) {
			buf.append(values.getArray()[i]).append(',');
		}
		return Response.ok("kittens=" + buf.toString()).build();
	}
}
