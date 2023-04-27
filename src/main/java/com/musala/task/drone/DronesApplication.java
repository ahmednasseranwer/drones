package com.musala.task.drone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DronesApplication {

	private static final Logger logger = LoggerFactory.getLogger(DronesApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(DronesApplication.class, args);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
