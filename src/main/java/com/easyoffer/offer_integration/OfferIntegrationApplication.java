package com.easyoffer.offer_integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OfferIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferIntegrationApplication.class, args);
	}

}
