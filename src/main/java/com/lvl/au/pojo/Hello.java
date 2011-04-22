package com.lvl.au.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Hello extends RestPojo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String greeting;
	@XmlTransient
	private int version;
	@XmlTransient
	private Date lastModified;

	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getGreeting() {
		return greeting;
	}
	public final void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public final int getVersion() {
		return version;
	}
	public final void setVersion(int version) {
		this.version = version;
	}
	public final Date getLastModified() {
		return lastModified;
	}
	public final void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hello [greeting=").append(greeting).append(", id=").append(id).append(", lastModified=")
				.append(lastModified).append(", version=").append(version).append("]");
		return builder.toString();
	}
}
