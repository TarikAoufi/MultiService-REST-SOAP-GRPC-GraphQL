package fr.tao.customerservice.grpc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.tao.customerservice.grpc.generated.CustomerRequest;
import fr.tao.customerservice.grpc.generated.CustomerServiceGrpc;
import fr.tao.customerservice.grpc.generated.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * This class contains test methods for the Customer gRPC service. 
 * 
 * It performs unit tests to ensure the functionality of the service, 
 * including retrieving all customers and creating a new customer.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerGrpcServiceTest {
		
    private static ManagedChannel channel;
    private static CustomerServiceGrpc.CustomerServiceBlockingStub blockingStub;
    
    /**
     * Set up the test environment by creating a gRPC channel and blocking stub 
     * for communication with the CustomerService server before running the tests.
     *
     * @throws Exception if an error occurs during setup
     */
    @BeforeAll
    public static void setUp() throws Exception {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        blockingStub = CustomerServiceGrpc.newBlockingStub(channel);
    }
    
    /**
     * Shuts down the gRPC channel after all tests are executed.
     *
     * @throws InterruptedException if there is an issue during channel shutdown.
     */
    @AfterAll
    public static void tearDown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    
    /**
     * Test the functionality of retrieving all customers from the CustomerService.
     *
     * @throws Exception if an error occurs during the test
     */
	@Test
    void getAllCustomerSuccess() throws Exception {
        var empty = Empty.newBuilder().build();
        var customerList = blockingStub.getAllCustomers(empty);       
        
        // Assert that the customer list is not null and contains at least one customer
        assertNotNull(customerList);
        assertTrue(customerList.getCustomersList().size() > 0);
    }
	
	/**
     * Test the functionality of creating a new customer using the CustomerService.
     */
    @Test
    void createCustomerSuccess() {
        // Create a CustomerRequest with the desired data
        var request = CustomerRequest.newBuilder()
                .setName("Toto")
                .setEmail("toto@gmail.com")
                .build();

        // Send the request and receive the response
        var response = blockingStub.createCustomer(request);

        // Validate the response
        assertNotNull(response);
        assertTrue(response.getId() > 0);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getEmail(), response.getEmail());
    }

}
