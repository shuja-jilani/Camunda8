package com.camunda.camunda8poc;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class Camunda8pocApplication {

	public static void main(String[] args) {
		SpringApplication.run(Camunda8pocApplication.class, args);
	}

}
