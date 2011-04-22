package com.lvl.au.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lvl.au.pojo.Cat;

public class CatServiceTest {

	@Test
	public void doit() {
		CatService service = new CatService();
		Cat cat = new Cat();
		String breed = "Tabby";
		cat.setBreed(breed);
		Cat newCat = service.addCat(cat);
		assertNotNull(newCat.getId());
		assertEquals(breed, newCat.getBreed());
	}
}
