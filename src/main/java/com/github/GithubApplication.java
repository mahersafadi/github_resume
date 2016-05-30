package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath:config.xml")
@SpringBootApplication
public class GithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubApplication.class, args);
	}
}
