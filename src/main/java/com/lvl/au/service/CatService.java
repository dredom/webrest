package com.lvl.au.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.lvl.au.pojo.Cat;
import com.sun.jersey.api.NotFoundException;

/**
 * POE - Post Once Exactly.
 * @author auntiedt
 *
 */
public class CatService {

	private static Map<Integer, Cat> cats = new HashMap<Integer, Cat>();
	private static int maxId;

	private static AtomicReference<String> poeId = new AtomicReference<String>();

	public Cat getCat(Integer id) {
		Cat world = cats.get(id);
		if (world == null) {
			throw new NotFoundException();
		}
		return world;
	}

	public Cat[] getAll() {
		return cats.values().toArray(new Cat[0]);
	}

	public Cat addCat(Cat world) {
		Integer id = maxId++;
		world.setId(id);
		world.setLastModified(new Date());
		cats.put(id, world);
		return world;
	}

	public Cat updateCat(Cat world) throws NotFoundException {
		Integer id = world.getId();
		Cat old = cats.get(id);
		if (old == null) {
			throw new NotFoundException();
		}
		world.setLastModified(new Date());
		world.setVersion(world.getVersion() + 1);
		cats.put(id, world);
		return world;
	}

	public boolean deleteCat(Integer id) {
		Object found = cats.remove(id);
		return found != null;
	}

	public final String generatePoeId() {
		poeId.set(UUID.randomUUID().toString().intern());
		return poeId.get();
	}

	public final String getPoeId() {
		return poeId.get();
	}

	public boolean claimPoeId(String poeId) {
		return this.poeId.compareAndSet(poeId.intern(), null);
	}
}
