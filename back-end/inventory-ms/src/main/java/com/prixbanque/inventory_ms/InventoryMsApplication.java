package com.prixbanque.inventory_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.prixbanque.inventory_ms.repository")
@EntityScan(basePackages = "com.prixbanque.inventory_ms.model")
public class InventoryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMsApplication.class, args);
	}

}
