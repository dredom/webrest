package com.lvl.au.pojo.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerWrapper {
	private Integer value;

	public IntegerWrapper() {
		super();
	}
	public IntegerWrapper(Integer value) {
		super();
		this.value = value;
	}

	public final Integer getValue() {
		return value;
	}

	public final void setValue(Integer value) {
		this.value = value;
	}
}
