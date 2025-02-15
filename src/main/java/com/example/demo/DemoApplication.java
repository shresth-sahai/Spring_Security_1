package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
/*
basic auth -> Spring boot applies a basic auth -> username, password ->
 end points -> /borrow -> use spring security
 users / roles -> ADMIN USER MODERATOR etc

 */
