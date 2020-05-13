package com.cesi.seatingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SeatingPlanAPI {

	public static void main(String[] args) {
		SpringApplication.run(SeatingPlanAPI.class, args);
	}
}

