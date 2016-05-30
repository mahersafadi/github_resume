package com.github.service.impl;

/**
 * @author maher
 * This service is responsible for receive the request from ReST layer and pass 
 * the github user name to the profile builder
 * which in its turn build the profile
 */
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

import com.github.service.GithubService;
import com.github.transferobjects.Language;
import com.github.transferobjects.Profile;

public class GithubServiceImpl implements GithubService {
	RepositoryService repoService;
	UserService userService;

	public GithubServiceImpl() {
		repoService = new RepositoryService();
		userService = new UserService();
	}
	private boolean isValidUser(String githubUserName){
		if(githubUserName == null || "".equals(githubUserName) || githubUserName.contains(" "))
			return false;
		return true;
	}
	@Override
	public Profile getProfile(String githubUserName) throws IOException {
		if(!isValidUser(githubUserName))
			throw new RuntimeException("Invalid github user");
		User user = userService.getUser(githubUserName);
		return new ProfileBuilder().buildBasics(repoService.getRepositories(githubUserName)).buildUsersIssues(user)
				.produce();
	}

	/**
	 * 
	 * @author maher Becuse this class is thread safe, so it is OK to create the
	 *         profile befor start building
	 */
	static final class ProfileBuilder {
		private final Profile profile;
		private Map<String, Integer> languages;
		private int languagesCount;
		private int totalForks;

		public ProfileBuilder() {
			this.profile = new Profile();
			this.languages = new HashMap<>();
		}

		private void buildLanguages() {
			if (languagesCount == 0)
				languagesCount = 1;
			for (Map.Entry<String, Integer> entry : languages.entrySet()) {
				this.profile.addLanguage(new Language(entry.getKey(), (100 * entry.getValue()) / languagesCount));
			}
		}

		/**
		 * this private method is build each repositorties one by one by passing
		 * a git hub repository then get the needed information from it and add
		 * it to profile repositories
		 * 
		 * @param currentGithubRepo
		 */
		private void buildRepository(Repository currentGithubRepo) {
			com.github.transferobjects.Repository profileRepo = new com.github.transferobjects.Repository();
			profileRepo.setName(currentGithubRepo.getName());
			Calendar c = Calendar.getInstance();
			c.setTime(currentGithubRepo.getPushedAt());
			profileRepo.setCreateionYear(c.get(Calendar.YEAR));
			int forks = currentGithubRepo.getForks();
			profileRepo.setForks(forks);
			totalForks += forks;
			profileRepo.setUrl(currentGithubRepo.getHtmlUrl());
			profileRepo.setDescription(currentGithubRepo.getDescription());
			this.profile.addRepository(profileRepo);
			// process Language
			String lang = currentGithubRepo.getLanguage();
			if (lang != null && !"".equals(lang)) {
				if (this.languages.containsKey(lang)) {
					this.languages.put(lang, this.languages.get(lang) + 1);
				} else
					this.languages.put(lang, 1);
				this.languagesCount++;
			}
		}

		/**
		 * This method is used to build the main items in the profile, like
		 * repositories and languages
		 * 
		 * @param repositories
		 *            the list of repositories come from github
		 * @return the builder to self
		 * @throws IOException
		 *             if there is a problems during get the data from github
		 */
		ProfileBuilder buildBasics(List<Repository> repositories) throws IOException {
			for (Repository currentGithubRepo : repositories) {
				this.buildRepository(currentGithubRepo);
			}
			buildLanguages();
			profile.setForks(totalForks);
			return this;
		}

		/**
		 * This method is used to build the main items in the profile, like
		 * repositories and languages
		 * 
		 * @param user:
		 * @return the builder it self
		 * @throws IOException
		 *             if there is a problems during get the data from github
		 */
		ProfileBuilder buildUsersIssues(User user) throws IOException {
			this.profile.setFollowers(user.getFollowers());
			this.profile.setEmail(user.getEmail());
			this.profile.setName(user.getHtmlUrl());
			return this;
		}

		Profile produce() {
			return profile;
		}
	}
}
