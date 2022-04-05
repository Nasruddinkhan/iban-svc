package com.mypractice.iban;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "IBAN API", version = "2.0", description = "Iban Information"))
@SpringBootApplication
public class IbanSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbanSvcApplication.class, args);
	}

}
