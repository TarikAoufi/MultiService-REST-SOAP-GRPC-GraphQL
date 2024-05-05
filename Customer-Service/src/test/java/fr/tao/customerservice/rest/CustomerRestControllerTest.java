package fr.tao.customerservice.rest;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import fr.tao.customerservice.api.rest.CustomerRestController;
import fr.tao.customerservice.common.CommonTestSetup;
import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.exception.CustomerNotFoundException;
import fr.tao.customerservice.service.CustomerService;


/**
 * Unit tests for the CustomerRestController class, which handles RESTful API requests,  
 * using the Spring WebFlux framework.
 * 
 * It tests various HTTP request methods such as GET, POST, PUT, and DELETE to ensure 
 * that the REST API endpoints work as expected, including both successful and error scenarios.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@WebFluxTest(CustomerRestController.class)
class CustomerRestControllerTest extends CommonTestSetup {
    
    @MockBean
    private CustomerService customerService;
          
    @BeforeEach
    public void setUp() { 	
    	// Initialize test data before each test case
    	initTestData();
    }
    
    // ================  GET request handler ================
    /**
     * Test the GET request handler for retrieving all customers, expecting a successful response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void getAllCustomerSuccess() throws Exception {
    	// Mock service call to return a list of customers
    	when(customerService.getAllCustomer()).thenReturn(customers); 
    	
	webTestClient
	    .get().uri(REST_ENDPOINT)
	    .accept(MediaType.APPLICATION_JSON)
	    .exchange()
	    .expectStatus().isOk()
	    .expectBodyList(CustomerDto.class).isEqualTo(customers);
    }
    
    /**
     * Test the GET request handler for retrieving a customer by their ID, expecting a successful response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void getCustomerByIdSuccess() throws Exception {
    	// Mock service call to return a specific customer
    	when(customerService.getCustomerById(customer3.getId())).thenReturn(customer3);
    	
    	webTestClient.get()
    	    .uri(REST_ENDPOINT_ID, customer3.getId())
    	    .accept(MediaType.APPLICATION_JSON)
    	    .exchange()
            .expectStatus().isOk()
            .expectBody(CustomerDto.class)
            .isEqualTo(customer3);
    }
    
    /**
     * Test the GET request handler for retrieving a customer by their ID, expecting a "Not Found" response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void getCustomerByIdNotFound() throws Exception {
        Long nonExistentCustomerId = 99L;
        
        // Mock the service to throw a CustomerNotFoundException (404 NOT_FOUND)
        when(customerService.getCustomerById(nonExistentCustomerId))
            .thenThrow(new CustomerNotFoundException(nonExistentCustomerId));

        webTestClient.get()
            .uri(REST_ENDPOINT_ID, nonExistentCustomerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }
    
    
    // ================ POST request handler ================ 
    /**
     * Test the POST request handler for creating a new customer, expecting a successful response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void createCustomerSuccess() throws Exception {
 		
	when(customerService.saveCustomer(newCustomer)).thenReturn(newCustomer);
		
	webTestClient.post()
            .uri(REST_ENDPOINT)
	    .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(newCustomer)
	    .exchange()
	    .expectStatus().isCreated()
	    .expectBody(CustomerDto.class)
	    .isEqualTo(newCustomer);
    }
 	
    // ================ PUT request handler ================  	
    /**
     * Test the PUT request handler for updating a customer, expecting a successful response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void testUpdateCustomer() throws Exception {		
 		
        when(customerService.updateCustomer(customer3.getId(), updatedCustomer)).thenReturn(updatedCustomer);

        webTestClient.put()
 	        .uri(REST_ENDPOINT_ID, customer3.getId())
 	        .contentType(MediaType.APPLICATION_JSON)
 	        .bodyValue(updatedCustomer)
 	        .exchange()
 	        .expectStatus().isOk()
 	        .expectBody(CustomerDto.class)
 	        .isEqualTo(updatedCustomer);
    }
 	
    // ================ DELETE request handler ================	
    /**
     * Test the DELETE request handler for deleting a customer, expecting a successful response.
     *
     * @throws Exception if an error occurs during testing
     */
    @Test
    void deleteCustomerSuccess() throws Exception {
 	Long customerId = customerToDelete.getId();
 		
 	// Mock the service to return the customer to be deleted
        when(customerService.getCustomerById(customerId)).thenReturn(customerToDelete);      
        doAnswer(invocation -> {
            // Perform actions after calling deleteCustomer
            return null; // or any other value to return
        }).when(customerService).deleteCustomer(customerId);
       
        // Perform the DELETE request
        webTestClient
                .delete()
                .uri(REST_ENDPOINT_ID, customerId)
                .exchange()
                .expectStatus().isOk();
        
        // Verify that the deleteCustomer method was called exactly once
        verify(customerService, times(1)).deleteCustomer(customerId);
    }

}
