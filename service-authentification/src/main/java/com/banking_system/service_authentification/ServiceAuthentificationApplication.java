package com.banking_system.service_authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceAuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthentificationApplication.class, args);
	}

}
