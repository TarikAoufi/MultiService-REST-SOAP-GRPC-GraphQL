package fr.tao.customerserviceclient.service.rest;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.common.RemoteApiProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class acts as a client to interact with a remote API for managing customer data.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerRestTemplateClient {
	
	/**
	 * Configuration properties for the remote API.
	 */
	@NonNull 
	private final RemoteApiProperties remoteApiProperties;	
	
	/**
     * A RestTemplate instance used for making HTTP requests to the remote API.
     */
	@NonNull 
	private final RestTemplate restTemplate;
	
	/**
     * Retrieves a list of all customers from the remote API.
     *
     * @return A list of Customer objects representing all customers.
     */
	public List<Customer> getAllCustomers() {
		log.info("CustomerRestTemplateClient - Fetching all customers");
		// Make a GET request to the remote API to retrieve all customers
		var customers = restTemplate.getForObject(
				remoteApiProperties.getRemoteApiUrl(remoteApiProperties.getUriCustomers()), 
				Customer[].class);	
		// Convert the array of customers to a List and return it.
		return List.of(customers);
	}
	
	/**
     * Retrieves a specific customer by their ID from the remote API.
     *
     * @param id The ID of the customer to retrieve.
     * @return The Customer object with the specified ID, or null if not found.
     */
	public Customer getCustomerById(Long id) {
		log.info("CustomerRestTemplateClient - Fetching customer with ID: {}", id);
		
		// Make a GET request to the remote API to retrieve a specific customer by ID.
	    var customer = restTemplate.getForObject(
	    		remoteApiProperties.getRemoteApiUrl(remoteApiProperties.getUriCustomers()) + "/" + id, 
	    		Customer.class
	    );
	    
	    if (customer != null) {
	        log.info("CustomerRestTemplateClient - Fetched customer with ID {}: {}", id, customer);
	    } else {
	        log.warn("CustomerRestTemplateClient - Customer with ID {} not found", id);
	    }
	    return customer;
	}

}
