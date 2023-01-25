package com.example.ProjectElder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProjectElderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectElderApplication.class, args);
	}

}
