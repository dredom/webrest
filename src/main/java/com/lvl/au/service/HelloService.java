package com.lvl.au.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lvl.au.pojo.Hello;
import com.sun.jersey.api.NotFoundException;

public class HelloService {

	private static Map<Integer, Hello> hellos = new HashMap<Integer, Hello>();
	private static int maxId;

	public Hello getHello(Integer id) {
		Hello world = hellos.get(id);
		if (world == null) {
			throw new NotFoundException();
		}
		return world;
	}

	public Hello[] getAll() {
		return hellos.values().toArray(new Hello[0]);
	}

	public Hello postHello(Hello world) {
		Integer id = maxId++;
		world.setId(id);
		world.setLastModified(new Date());
		hellos.put(id, world);
		return world;
	}

	public Hello putHello(Hello world) throws NotFoundException {
		Integer id = world.getId();
		Hello old = hellos.get(id);
		if (old == null) {
			throw new NotFoundException();
		}
		world.setLastModified(new Date());
		world.setVersion(world.getVersion() + 1);
		hellos.put(id, world);
		return world;
	}

	public boolean deleteHello(Integer id) {
		Object found = hellos.remove(id);
		return found != null;
	}
}
