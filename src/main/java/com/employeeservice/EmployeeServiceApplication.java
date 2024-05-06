package com.employeeservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.employeeservice.feignclient.AddressClient;

@SpringBootApplication
@EnableFeignClients(clients = AddressClient.class)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
