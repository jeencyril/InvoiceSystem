package com.invoice.InvoiceSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.invoice.services", "com.invoice.controllers","com.invoice.repository"})
public class InvoiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceSystemApplication.class, args);
	}

}
