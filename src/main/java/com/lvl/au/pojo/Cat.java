package com.lvl.au.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Feline objective.
 * @author auntiedt
 * @version 1.0
 */
@XmlRootElement
public class Cat extends RestPojo {
	private static final long serialVersionUID = 2L;
	private Integer id;
	private String breed;
	private int version;
	private Date lastModified;

	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return {@link String} type of cat
	 */
	public final String getBreed() {
		return breed;
	}
	/**
	 * @param breed Type of cat
	 */
	public final void setBreed(String breed) {
		this.breed = breed;
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
		builder.append("Hello [greeting=").append(breed).append(", id=").append(id).append(", lastModified=")
				.append(lastModified).append(", version=").append(version).append("]");
		return builder.toString();
	}
}
