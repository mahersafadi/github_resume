package com.github.transferobjects;
import java.io.Serializable;

/**
 * @author maher Each language in the profile contains the name of the language
 *         and the percentage of its use in the profile
 */
public class Language implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7141541356815679257L;
	
	private String name;
	private float percentage;

	public Language() {

	}

	public Language(String name, float percentage) {
		this.name = name;
		this.percentage = percentage;
	}

	public String getName() {
		return name;
	}

	public float getPercentage() {
		return percentage;
	}
}
