package com.lvl.au.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.lvl.au.service.FileOutput;
import com.lvl.au.service.FileService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.MultiPart;

/**
 * References Jersey classes to accomplish the file upload.
 */
@Path("file")
public class FileResource {

	FileService service = new FileService();

	@Context UriInfo urii;
	@Context Request request;
	@Context HttpHeaders headers;

	@POST
	@Path("{name}")
	@Consumes("multipart/form-data")
	public Response addFile(@PathParam("id") String id, @PathParam("name") String name, InputStream fileIs) throws Exception {
		int bytes = service.save(name, fileIs);

		ResponseBuilder builder = Response.created(urii.getRequestUri());	// status 201
		builder.entity(name + " " + bytes);

		// The inputstream is everything, including the headers.
		return builder.build();
	}

	@POST
	@Path("upform")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadForm(FormDataMultiPart form ) {
		FormDataBodyPart part = form.getField("file");
		String name = part.getName();
		String txt = name + ":" + (part.isSimple() ? "simple" : "complex");
		if (part.isSimple()) {
			txt += ":" + part.getValue();	// value is file contents
		}
		return Response.ok(txt).build();
	}

	@POST
	@Path("up")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFileData(
			@DefaultValue("true") @FormDataParam("enabled") boolean enabled,
			@FormDataParam("file") InputStream file,
			@FormDataParam("file") FormDataContentDisposition disposition
			) throws IOException {
		String filename = disposition.getFileName();
//		MultivaluedMap<String, String> cdh = headers.getRequestHeaders();
//		Set<String> keys = cdh.keySet();
//		for (String key : keys) {
//			System.out.printf("  %s: %s \n", key, cdh.getFirst(key));
//		}
		System.out.println(filename);
		int bytes = service.save(filename, file);

		URI location = urii.getAbsolutePathBuilder()
			.segment(filename)
			.build();

		return Response.created(location)	// status 201
			.entity(filename + " " + bytes)
			.build();
	}

	@GET
	@Path("{filename}")
	public Response getFile(@PathParam("filename") String filename) throws Exception {
		FileOutput out = service.get(filename);
		return Response.ok(out, "image/png").build();
	}

	@POST
	@Path("upmulti")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMulti(MultiPart multi ) {
		List<BodyPart> parts = multi.getBodyParts();
		String txt = parts.size() + " parts";

		if (parts.size() > 0) {
			BodyPart part = parts.get(0);
			txt += ":" + part.toString();
			txt += ":" + part.getMediaType();	// parts:com.sun.jersey.multipart.FormDataBodyPart@779f5e:text/plain
		}
		return Response.ok(txt).build();

	}
}
