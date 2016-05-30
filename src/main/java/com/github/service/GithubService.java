package com.github.service;
/**
 * @author maher
 * This service is responsible for receive the request from ReST layer and pass 
 * the github user name to the profile builder
 * which in its turn build the profile
 */
import java.io.IOException;

import com.github.transferobjects.Profile;

public interface GithubService {
	/**
	 * called by ReST layer to build the profile for the passed user
	 * @param githubUserName the user wanted to be build a profile for him
	 * @return	The build profile if every thing is passed OK
	 * @throws IOException if there is a problems raised during build the profile
	 */
	public Profile getProfile(String githubUserName) throws IOException;
}
