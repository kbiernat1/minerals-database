package com.crud.minerals;

import com.crud.minerals.domain.Mineral;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MineralsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MineralsApplication.class, args);
	}
}
