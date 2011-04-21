package com.lvl.au.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Array of type works. Array of generic type runs but returns nulls.
 * Same with List<E> runs but returns nulls.
 * @author auntiedt
 *
 */
@XmlRootElement
public class Items<E> {
	private List<E> list;

	public final List<E> getList() {
		return list;
	}

	public final void setList(List<E> list) {
		this.list = list;
	}



}
