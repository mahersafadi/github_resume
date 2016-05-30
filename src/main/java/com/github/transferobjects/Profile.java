package com.github.transferobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maher
 * 
 *         This class is the main transfer object in the application
 *         It is produced by ReST Layer and prepared in ProfileBuilder class by GitHubServiceImpl
 */

public class Profile implements TransferObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8365022417288853155L;
	private String name;
	private String email;
	private int forks;
	private int followers;
	private List<Language> languages;
	private List<Repository> repositories;

	public Profile() {
		this.languages = new ArrayList<>();
		this.repositories = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addLanguage(Language lang) {
		this.languages.add(lang);
	}

	public void addRepository(Repository repository) {
		this.repositories.add(repository);
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public List<Repository> getRepositories() {
		return repositories;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public int getForks() {
		return forks;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
