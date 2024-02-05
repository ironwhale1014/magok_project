package com.honeybee.magok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MagokApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagokApplication.class, args);
	}

}
