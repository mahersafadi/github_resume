package com.github.transferobjects;
/**
 * @author maher
 * This class represents the repository in github
 */
import java.io.Serializable;

public class Repository implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7921697260952080108L;
	private String url;
	private String name;
	private int forks;
	private int year;
	private String description;

	public void setCreateionYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public int getForks() {
		return forks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
