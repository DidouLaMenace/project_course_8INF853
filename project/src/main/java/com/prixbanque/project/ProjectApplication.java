package com.prixbanque.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.prixbanque.project")
@ComponentScan(basePackages = "com.prixbanque.project")
@EnableJpaRepositories(basePackages = "com.prixbanque.project.account")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
