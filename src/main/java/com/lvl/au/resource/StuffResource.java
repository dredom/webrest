package com.lvl.au.resource;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

@Path("stuff")
public class StuffResource {
	private String[] dateParsePatterns = {
			DateFormatUtils.ISO_DATE_FORMAT.getPattern(),
			DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.getPattern(),
			DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern(),
		};

	@PUT
	@Path("qparams")
	public void putWithQueryParams(@QueryParam("qpa") String qpa) {
		System.out.println("qpa=" + qpa);
	}

	@POST
	@Path("renoir")
	public Response replaceLastSegment(@Context UriInfo uriInfo) {
		List<PathSegment> segments = uriInfo.getPathSegments();
		UriBuilder builder = uriInfo.getRequestUriBuilder().replacePath("");
		segments.remove(segments.size() - 1);
		for (PathSegment segment : segments) {
			builder.path(segment.getPath());
		}
		URI location = builder.segment("123").build();
		return Response.ok()
			.location(location)
			.build();
	}

	@GET
	@Path("search")
	public Response search(
			@QueryParam("date") String date) {

		try {
			Date pdate = DateUtils.parseDate(date, dateParsePatterns);
			return Response.ok(pdate.toString())
				.build();
		} catch (Exception e) {
			return Response.status(400)
				.entity("Formats are: " + Arrays.deepToString(dateParsePatterns))
				.build();
		}

	}
}
