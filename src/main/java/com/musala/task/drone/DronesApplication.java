package com.musala.task.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DronesApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(DronesApplication.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
