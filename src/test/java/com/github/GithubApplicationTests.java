package com.github;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.service.GithubService;
import com.github.transferobjects.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GithubApplication.class)
@WebAppConfiguration
public class GithubApplicationTests {

	@Autowired
	private GithubService githubService;

	@Test
	public void contextLoads() throws IOException {
		
		Profile profile = githubService.getProfile("mahersafadi");
		assertNotNull(profile);
		assertNotEquals(profile.getRepositories(), 0);
		assertNotEquals(profile.getName(), null);
	}
	
}
