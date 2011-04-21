package com.lvl.au.service;

import java.util.HashMap;
import java.util.Map;

import com.lvl.au.pojo.World;
import com.sun.jersey.api.NotFoundException;

public class WorldService {

	private static Map<Integer, World> population = new HashMap<Integer, World>();
	private static int maxId;

	public World getWorld(Integer id) {
		World world = population.get(id);
		if (world == null) {
			throw new NotFoundException();
		}
		return world;
	}

	public World postWorld(World world) {
		Integer id = maxId++;
		world.setId(id);
		population.put(id, world);
		return world;
	}

	public World putWorld(World world) throws NotFoundException {
		Integer id = world.getId();
		World old = population.get(id);
		if (old == null) {
			throw new NotFoundException();
		}
		population.put(id, world);
		return world;
	}

	public void deleteWorld(Integer id) {
		Object found = population.remove(id);
	}
}
