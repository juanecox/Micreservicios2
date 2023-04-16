package com.rentasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RentasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentasServiceApplication.class, args);
	}

}
