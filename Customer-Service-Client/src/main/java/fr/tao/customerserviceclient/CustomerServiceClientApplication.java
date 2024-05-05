package fr.tao.customerserviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import fr.tao.customerserviceclient.common.RemoteApiProperties;
import fr.tao.customerserviceclient.service.grpc.GrpcCustomerServiceClient;
import fr.tao.customerserviceclient.soap.generated.CustomerSoapService;
import fr.tao.customerserviceclient.soap.generated.CustomerWS;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** 
 * This class is the main entry point for the Customer Service Client application.
 * 
 * It is responsible for configuring and creating beans required for interacting 
 * with various remote APIs, including REST, GraphQL, SOAP, and gRPC.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceClientApplication {
	
    /**
     * Configuration properties for the remote API.
     */
    @NonNull 
    private final RemoteApiProperties remoteApiProperties;
	
    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
	log.info("Starting CustomerServiceApplication...");
	SpringApplication.run(CustomerServiceClientApplication.class, args);
    }
	
    /**
     * Creates and configures a RestTemplate bean for making REST API calls.
     * 
     * @return A RestTemplate instance.
     */
    @Bean
    RestTemplate restTemplate() {
	return new RestTemplate();
    }
	
	/**
	 * Creates and configures a WebClient bean for making web-based API calls 
	 * with default JSON content type.
	 * 
	 * @return A WebClient instance.
	 */
    @Bean
    WebClient webClient() {
        return WebClient.builder()
        		.baseUrl(remoteApiProperties.getBaseUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.build();
    }
	
	/**
	 * Creates and configures an HTTP GraphQL client for making GraphQL API calls.
	 * 
	 * @return An HttpGraphQlClient instance.
	 */
	@Bean
	HttpGraphQlClient httpGraphQlClient() {
		return HttpGraphQlClient.builder()
				.url(remoteApiProperties.getRemoteApiUrl(remoteApiProperties.getUriGraphql()))
				.build();
	}
	
	/**
	 * Creates a bean for the Customer SOAP service using the provided properties.
	 * 
	 * @return A CustomerSoapService instance.
	 */
	@Bean
	CustomerSoapService customerSoapService() {
		return new CustomerWS().getCustomerSoapServicePort();
	}
	
	/**
	 * Creates a gRPC Customer Service Client.
	 * 
	 * @return A GrpcCustomerServiceClient instance.
	 */
	@Bean
	GrpcCustomerServiceClient grpcCustomerServiceClient() {
		return new GrpcCustomerServiceClient();
	} 

}
