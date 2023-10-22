package fr.tao.customerserviceclient.service.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.common.RemoteApiProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class is a service for making web requests to a remote API to interact with customer data.
 *
 * It provides methods for retrieving customer data using a reactive WebClient.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerWebClient {
	
	/**
	 * Configuration properties for the remote API.
	 */
    @NonNull 
    private final RemoteApiProperties remoteApiProperties;	
    
    /**
     * A WebClient instance for making HTTP requests.
     */
    @NonNull 
    private final WebClient webClient;
    
    /**
     * Retrieves a Flux of all customers from the remote API.
     *
     * @return A Flux of Customer objects representing all customers.
     */
	public Flux<Customer> getAllCustomers() {
		log.info("CustomerWebClient - Fetching all customers from the remote API.");
		return webClient.get()
				.uri(remoteApiProperties.getUriCustomers())
				.retrieve()
				.bodyToFlux(Customer.class);
	}
	
	/**
     * Retrieves a Mono of a customer by their ID from the remote API.
     *
     * @param id The ID of the customer to retrieve.
     * @return A Mono of Customer representing the customer with the specified ID.
     */
	public Mono<Customer> getCustomerById(Long id) {
		log.info("CustomerWebClient - Fetching customer by ID: {}", id);
        return webClient.get()
        		.uri(remoteApiProperties.getUriCustomerById(), id)
	            .retrieve()
	            .bodyToMono(Customer.class);
    }

}
