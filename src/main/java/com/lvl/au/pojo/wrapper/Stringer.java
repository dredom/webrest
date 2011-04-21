package com.lvl.au.pojo.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stringer {
	private String value;
	private String[] array;

	public final String[] getArray() {
		return array;
	}

	public final void setArray(String[] array) {
		this.array = array;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}
}
