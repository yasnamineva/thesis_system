package com.uni.thesissystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.uni.thesissystem")
public class ThesisSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThesisSystemApplication.class, args);
	}
}
