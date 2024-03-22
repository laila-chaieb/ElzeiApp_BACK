package com.example.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = "com.example.operation") //  le package de la classe WebConfig 
@EnableScheduling
public class OperationApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(OperationApplication.class, args);
	}

}
