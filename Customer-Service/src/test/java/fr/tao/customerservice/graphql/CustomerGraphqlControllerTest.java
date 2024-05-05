package fr.tao.customerservice.graphql;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import fr.tao.customerservice.common.CommonTestSetup;
import fr.tao.customerservice.service.CustomerService;
import fr.tao.customerservice.util.MessageUtil;



/**
 * This class provides JUnit tests for the Customer GraphQL Controller: 'CustomerGraphqlController'.
 * 
 * It includes test methods for different GraphQL queries and mutations related to customer data.
 * The tests utilize the Spring WebTestClient to send GraphQL requests and validate the responses.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CustomerGraphqlControllerTest extends CommonTestSetup {
	
    @Mock
    protected CustomerService customerService;
	
    @BeforeEach
    public void setUp() { 
    	// Create test data before each test
    	createTestCustomers(); 
    	createNewCustomer();
    }
	
    /**
     * Test case for querying all customers via GraphQL.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    void getAllCustomerSuccess() throws Exception {		  	
    	// Mock service call to return a list of customers
    	when(customerService.getAllCustomer()).thenReturn(customers);
    	
        webTestClient.post()
                .uri(GQL_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(MessageUtil.GET_ALL_CUSTOMER_GQL_QUERY)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.allCustomer[1].name").isEqualTo(customer2.getName());
    }
	
    /**
     * Test case for querying a customer by their ID via GraphQL.
     */
    @Test
    void getCustomerByIdSuccess() {
	// Mock service call to return a specific customer by ID
    	when(customerService.getCustomerById(customer3.getId())).thenReturn(customer3);
    	
        webTestClient.post()
                .uri(GQL_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(MessageUtil.GET_CUSTOMER_BY_ID_GQL_QUERY.formatted(customer3.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.customerById.email").isEqualTo(customer3.getEmail());
    }
	
    /**
     * Test case for querying customers by their name via GraphQL.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    void getCustomerByNameSuccess() throws Exception {
	// Create a list of customers matching the specified name
	var customers = new ArrayList<>(Arrays.asList(customer1));  
		
	// Create the GraphQL query with the customer's name
	var graphqlQuery = String.format(MessageUtil.GET_CUSTOMER_BY_NAME_GQL_QUERY, customer1.getName());
		
        // Serialise the GraphQL query in JSON
        var requestBody = objectMapper.writeValueAsString(Map.of("query", graphqlQuery));
		
	// Mock service call to return customers by name
    	when(customerService.getCustomersByNameContains(customer1.getName())).thenReturn(customers);
		
        webTestClient.post()
                .uri(GQL_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.customerByName[0].name").isEqualTo(customer1.getName());
    }
	
    /**
     * Test case for saving a new customer via GraphQL.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    void saveCustomerSuccess() throws Exception {
	// Create the GraphQL mutation to save a new customer
        var graphqlQuery = String.format(
            MessageUtil.SAVE_CUSTOMER_GQL_MUTATION,
            newCustomer.getName(),
            newCustomer.getEmail()
        );

        // Serialise the GraphQL mutation into JSON
        var requestBody = objectMapper.writeValueAsString(Map.of("query", graphqlQuery));
		
        when(customerService.saveCustomer(newCustomer)).thenReturn(newCustomer);
        
        webTestClient.post()
                .uri(GQL_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.saveCustomer.name").isEqualTo(newCustomer.getName());
    }

}
