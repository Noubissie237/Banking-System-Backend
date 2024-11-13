package com.banking_system.service_retrait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRetraitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRetraitApplication.class, args);
	}

}
