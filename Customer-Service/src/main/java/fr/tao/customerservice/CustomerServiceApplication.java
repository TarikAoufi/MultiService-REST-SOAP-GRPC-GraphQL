package fr.tao.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.service.CustomerService;
import fr.tao.customerservice.util.MessageUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents the main application for the Customer Service.
 * It is responsible for starting the Spring Boot application and initializing some sample customer data.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = MessageUtil.API_REST_DOC_TITLE, version = "1.0", description = MessageUtil.API_REST_DOC_DESCRIPTION))
@Slf4j
public class CustomerServiceApplication {
	
	/**
     * The main method to start the CustomerServiceApplication.
     *
     * @param args Command-line arguments.
     */
	public static void main(String[] args) {
		log.info("Starting CustomerServiceApplication...");
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	/**
     * This method defines a command-line runner bean that initializes sample customer data.
     *
     * @param customerService The CustomerService instance to use for saving customer data.
     * @return A CommandLineRunner that initializes sample customer data.
     */
	@Bean
	CommandLineRunner run(CustomerService customerService) {		
		return args -> {		
			customerService.saveCustomer(new CustomerDto(0L, "momo", "momo@gmail.com"));
			customerService.saveCustomer(new CustomerDto(0L, "moha", "moha@gmail.com"));			
			customerService.saveCustomer(new CustomerDto(0L, "zakia", "zakia@gmail.com"));
			customerService.saveCustomer(new CustomerDto(0L, "ali", "ali@gmail.com"));
		};		
	}

}
