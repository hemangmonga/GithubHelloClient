package com.p1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GitHelloClientProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitHelloClientProjectApplication.class, args);
	}

}
