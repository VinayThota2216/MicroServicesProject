package com.vinay.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "REST Api's for Loans in Vinay's Bank",
				description = "REST APIs for managing customer loans in Vinay's Bank.",
				version = "v1",
				contact = @Contact(
						name = "Thota Vinay",
						email = "vinaythota543@gmail.com",
						url = "https://www.vinay.loans.com"
				),
				license = @License(
						name = "Apache License, Version 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Detailed API documentation for Vinay's Bank Loans REST APIs.",
				url = "https://www.vinay.loans.com/api-docs"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
