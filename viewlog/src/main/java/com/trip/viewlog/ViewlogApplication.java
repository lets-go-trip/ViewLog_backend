package com.trip.viewlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ViewlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewlogApplication.class, args);
	}

}
