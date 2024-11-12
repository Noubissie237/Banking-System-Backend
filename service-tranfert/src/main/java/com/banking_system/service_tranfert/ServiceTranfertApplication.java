package com.banking_system.service_tranfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceTranfertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTranfertApplication.class, args);
	}

}
