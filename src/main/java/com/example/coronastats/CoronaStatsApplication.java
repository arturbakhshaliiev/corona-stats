package com.example.coronastats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoronaStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaStatsApplication.class, args);
	}

}
