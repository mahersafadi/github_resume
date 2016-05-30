package com.github;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class JsApp {
	@RequestMapping("/")
	public ModelAndView display(){
		return new ModelAndView("redirect:index.html");
	}
}
