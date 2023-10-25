package com.learnthepart.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // instrument the launch of the spring boot app

// use maven wrapper to run maven command
// will compile this code into the target folder
public class HelloSpringApplication {

	public static void main(String[] args) {
		System.out.println("from console: Hello Spring Boot!");
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
