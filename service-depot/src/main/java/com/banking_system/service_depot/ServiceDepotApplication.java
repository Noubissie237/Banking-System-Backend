package com.banking_system.service_depot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceDepotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDepotApplication.class, args);
	}

}
