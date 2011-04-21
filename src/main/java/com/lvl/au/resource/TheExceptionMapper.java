package com.lvl.au.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.lvl.au.exception.BadParameterException;
import com.lvl.au.exception.NotFoundException;

@Provider
public class TheExceptionMapper implements ExceptionMapper<Exception> {

	@Context UriInfo urii;

	@Override
	public Response toResponse(Exception exception) {
		try {
			throw exception;
		} catch (com.sun.jersey.api.NotFoundException e) {
			ResponseBuilder result = Response.status(Status.NOT_FOUND);
			result.type(MediaType.TEXT_PLAIN);
			result.entity(urii.getRequestUri() + ": This does not RESTfully map to anything we know of, sir. Spelling? Wrong method? Try OPTIONS.");
			return result.build();

		} catch (BadParameterException e) {
			ResponseBuilder result = Response.status(Status.BAD_REQUEST);
			result.type(MediaType.TEXT_PLAIN);
			result.entity(urii.getRequestUri() + ": " + e.getMessage());
			return result.build();

		} catch (NotFoundException e) {
			ResponseBuilder result = Response.status(Status.NOT_FOUND);
			result.type(MediaType.TEXT_PLAIN);
			result.entity(urii.getRequestUri() + ": " + e.getMessage());
			return result.build();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			ResponseBuilder result = Response.status(Status.INTERNAL_SERVER_ERROR);
			result.type(MediaType.TEXT_PLAIN);
			result.entity(urii.getRequestUri() + ": " + e);
			return result.build();
		}
	}

}
