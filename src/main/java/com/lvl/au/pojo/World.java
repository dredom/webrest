package com.lvl.au.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class World {

	private Integer id;
	private String name;
	private EnumStatus status;

	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id=").append(id)
			.append(",name=").append(name)
			.append(",status=").append(status)
			.toString();
	}
	public final EnumStatus getStatus() {
		return status;
	}
	public final void setStatus(EnumStatus status) {
		this.status = status;
	}
}
