package com.lvl.au.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Array of type works. Array of generic type runs but returns nulls.
 * Same with List<E> runs but returns nulls.
 * @author auntiedt
 *
 */
@XmlRootElement
public class HelloItems {
	private Hello[] list;

	public final Hello[] getList() {
		return list;
	}

	public final void setList(Hello[] list) {
		this.list = list;
	}

}
