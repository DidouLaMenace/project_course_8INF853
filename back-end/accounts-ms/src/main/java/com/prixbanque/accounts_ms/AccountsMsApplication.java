package com.prixbanque.accounts_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccountsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsMsApplication.class, args);
	}

}
