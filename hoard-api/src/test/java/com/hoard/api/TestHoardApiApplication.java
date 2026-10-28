package com.hoard.api;

import org.springframework.boot.SpringApplication;

public class TestHoardApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(HoardApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
