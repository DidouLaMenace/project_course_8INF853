package com.prixbanque.commande_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommandeMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandeMsApplication.class, args);
	}

}
