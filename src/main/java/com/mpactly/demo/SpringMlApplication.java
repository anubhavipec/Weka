package com.mpactly.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.mpactly"})

public class SpringMlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMlApplication.class, args);
	}
}
