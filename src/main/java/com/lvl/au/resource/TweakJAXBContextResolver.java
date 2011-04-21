package com.lvl.au.resource;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.lvl.au.pojo.Hello;
import com.lvl.au.pojo.IntegerItems;
import com.lvl.au.pojo.World;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
public class TweakJAXBContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;
	private Class<?>[] types = { Hello.class, World.class, IntegerItems.class };

	/**
	 * @throws JAXBException
	 *
	 */
	public TweakJAXBContextResolver() throws JAXBException {
		super();
		this.context = new JSONJAXBContext(  JSONConfiguration.natural().build(), types);
	}

	@Override
	public JAXBContext getContext(Class<?> type) {
		System.out.println(" TweakJAXBContextResolver.getContext(" + type + ")");
		for (Class<?> classtype : types) {
			if (classtype == type) {
				return context;
			}
		}
		return null;
	}

}
