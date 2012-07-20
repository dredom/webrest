package com.lvl.au.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerItems {
	private Integer[] list;

	public final Integer[] getList() {
		return list;
	}

	public final void setList(Integer[] list) {
		this.list = list;
	}
}
