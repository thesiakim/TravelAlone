package com.travelAlone.s20230404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class S20230404Application {

	public static void main(String[] args) {
		SpringApplication.run(S20230404Application.class, args);
	}

}
