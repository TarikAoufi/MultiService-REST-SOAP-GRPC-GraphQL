package fr.tao.customerservice.soap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.tao.customerservice.api.soap.CustomerSoapService;
import fr.tao.customerservice.common.CommonTestSetup;
import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.service.CustomerService;

/**
 * Unit tests for the CustomerSoapService, which integrates with the CustomerService 
 * via SOAP requests.
 * 
 * These tests verify that the SOAP service correctly interacts with the underlying 
 * service to retrieve, create, and process customer data.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
/**
 * This class is a JUnit test class for the CustomerSoapService class. It tests the SOAP service methods
 * provided by the CustomerSoapService, such as getAllCustomer, getCustomerById, and createCustomer.
 */
class CustomerSoapServiceTest extends CommonTestSetup {
	
	@InjectMocks
	private CustomerSoapService customerSoapService;

    @Mock
    private CustomerService customerService;
    
    /**
     * Sets up the test environment before each test case execution.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize test data
        initTestData();
    }
    
    /**
     * Test case for testing the successful retrieval of all customers via the SOAP service.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void getAllCustomerSuccess() throws Exception { 	
        // Mock the behavior of customerService to return a list of customers
        Mockito.when(customerService.getAllCustomer()).thenReturn(customers);

        // Perform the SOAP service method call to get all customers.
        var result = customerSoapService.getAllCustomer();

        // Verify the results
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(customer1.getName(), result.get(0).getName());
        assertEquals(customer2.getName(), result.get(1).getName());
        assertEquals(customer3.getName(), result.get(2).getName());
    }
    
    
    /**
     * Test case for testing the successful retrieval of a customer by their ID via the SOAP service.
     */
    @Test
    void getCustomerByIdSuccess() {
    	// Mock the behavior of customerService to return a specific customer by ID
        Mockito.when(customerService.getCustomerById(customer3.getId())).thenReturn(customer3);

        // Perform the SOAP service method call to get a customer by ID
        var result = customerSoapService.customerById(customer3.getId());

        // Verify the result
        assertNotNull(result);
        assertEquals(customer3, result);
    }
    
    /**
     * Test case for testing the successful creation of a new customer via the SOAP service.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    void createCustomerSuccess() throws Exception {   	
    	// Mock the behavior of customerService to return 'newCustomer' when saving a customer.
        Mockito.when(customerService.saveCustomer(newCustomer)).thenReturn(newCustomer);

        // Perform the SOAP service method call to create a new customer
        CustomerDto result = customerSoapService.saveCustomer(newCustomer);

        // Verify the result
        assertNotNull(result);
        assertEquals(newCustomer, result);
    }

}
