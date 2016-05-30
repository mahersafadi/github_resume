package com.github.rest;

import java.io.IOException;

/**
 * @author maher
 * This class is responsible for receive the rest request 
 * from client and produces the profile or error if there is a problem raised
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.service.GithubService;
import com.github.transferobjects.Error;
import com.github.transferobjects.TransferObject;

@ComponentScan
@RestController
public class ResumeBuilderResouce extends AbstractResource{
	@Autowired
	private GithubService githubService;
	/**
	 * this method get the request(GET) contains the guthub username and produces the profile for the user
	 * @param name the github user coming from client
	 * @return the profile or an error if there is which the are implement TransferObject interface
	 */
	@ResponseBody
	@RequestMapping(value="/resume/{name}", produces = "application/json;charset=UTF-8")
	public TransferObject getProfile(@PathVariable("name") String name) {
		try {
			return githubService.getProfile(name);
		} catch (IOException ex) {
			ex.printStackTrace();
			Error e = new Error();
			e.setErrMsg("An error accured during get data from github, Please check the github username if correct, detailed message:"+ex.getMessage());
			return e;
		}
	}
}
