package fr.tao.customerservice.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.tao.customerservice.dto.CustomerDto;

/** 
 * This utility class provides shared attributes and test data 
 * for testing Customer-related operations.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023 
 */
public abstract class CommonTestSetup {
	
	@Autowired
	protected WebTestClient webTestClient;
	
	/**
     * ObjectMapper for JSON serialization/deserialization.
     */
	protected ObjectMapper objectMapper = new ObjectMapper();
	
	/**
     * Endpoint for GraphQL requests.
     */
	protected final String GQL_ENDPOINT = "/graphql";
	
	/**
     * Endpoint for REST API customers.
     */
	protected final String REST_ENDPOINT = "/api/customers";
	
	/**
     * Endpoint for retrieving a specific customer by ID.
     */
	protected final String REST_ENDPOINT_ID = REST_ENDPOINT+"/{id}";
	
	/**
     * List of CustomerDto objects used for testing.
     */
	protected List<CustomerDto> customers;
	
	/**
     * Various CustomerDto objects for testing different scenarios.
     */
	protected CustomerDto customer1, customer2, customer3, 
		newCustomer, updatedCustomer, customerToDelete;
	
	/**
     * Initializes test data by creating sample customers, a new customer, 
     * and updating one of the customers.
     * Additionally, a customer to be deleted is prepared.
     */
    public void initTestData() {     	
    	createTestCustomers(); 
    	createNewCustomer();
    	updateNewCustomer();
    	deleteCustomer();
    }
    
    /**
     * Creates a list of test customers with sample data.
     */
    public void createTestCustomers() {
        customer1 = new CustomerDto(1L, "momo", "momo@gmail.com");
        customer2 = new CustomerDto(2L, "moha", "moha@gmail.com");
        customer3 = new CustomerDto(3L, "zakia", "zakia@gmail.com");
        customers = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
    }
    
    /**
     * Creates a new customer with sample data.
     */
    public void createNewCustomer() {
    	newCustomer = new CustomerDto();
 	    newCustomer.setName("Titi");
		newCustomer.setEmail("titi@gmail.com");
    }
    
    /**
     * Updates the sample customer's data for testing.
     */
    public void updateNewCustomer() {
    	updatedCustomer = customer3;
    	updatedCustomer.setName("Zakia");
 	    updatedCustomer.setEmail("zakia.zakia@gmail.com");
    }
    
    /**
     * Prepares a customer for deletion with sample data.
     */
    public void deleteCustomer() {
    	customerToDelete = new CustomerDto(1L, "Toto", "toto@gmail.com"); 
    }

}
