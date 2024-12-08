package com.prixbanque.payments_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsMsApplication.class, args);
	}

}
