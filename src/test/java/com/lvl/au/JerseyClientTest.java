package com.lvl.au;

import static junit.framework.Assert.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.lvl.au.pojo.Hello;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientTest {

//	@Test
	public void getHelloString() {
		final String url = "http://localhost:8081/webrest/hello";
		Client client = Client.create();
		WebResource wr = client.resource(url);
		String result = wr.get(String.class);
		assertNotNull(result);
		assertTrue(result.toLowerCase().contains("world"));
	}

	@Test
	public void postHello() {
		final String url = "http://localhost:8081/webrest/hello";
		Client client = Client.create();
		WebResource wr = client.resource(url);
		Hello hell = new Hello();
		hell.setGreeting("Yo");
		ClientResponse response = wr
			.type(MediaType.TEXT_XML)
			.accept(MediaType.TEXT_XML)
			.post(ClientResponse.class, hell);
		assertNotNull(response);
		assertEquals("status", 201, response.getStatus());
		MultivaluedMap<String, String> heads = response.getHeaders();
		String location = heads.getFirst("Location");
		assertNotNull("location", location);
		Hello entity = response.getEntity(Hello.class);
		assertNotNull("entity", entity);
		assertTrue(entity.getGreeting().equals("Yo"));

		// Get created entity
		WebResource wr2 = client.resource(location);
		ClientResponse response2 = wr2
			.accept("text/xml")
			.get(ClientResponse.class);
		assertNotNull(response2);
		assertEquals("status", 200, response2.getStatus());
		Hello entity2 = response2.getEntity(Hello.class);
		assertNotNull("entity", entity2);
		assertTrue(entity2.getGreeting().equals("Yo"));
	}
}
