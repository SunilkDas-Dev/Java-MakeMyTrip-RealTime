package com.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Make-My-Trip Booking Online Ticket System", version = "1.0",
description = "Register Management App", contact = @Contact(name = "Sunil Kumar Das ", email = "kumardassunil2000@gmail.com")))

@SpringBootApplication
public class MakeMyTrip_Application {

	public static void main(String[] args) {
		SpringApplication.run(MakeMyTrip_Application.class, args);
		
	}

}
