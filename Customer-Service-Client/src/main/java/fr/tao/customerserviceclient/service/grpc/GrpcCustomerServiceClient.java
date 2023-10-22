package fr.tao.customerserviceclient.service.grpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.tao.customerserviceclient.model.Customer;
import lombok.extern.slf4j.Slf4j;
import fr.tao.customerserviceclient.grpc.generated.CustomerId;
import fr.tao.customerserviceclient.grpc.generated.CustomerServiceGrpc;
import fr.tao.customerserviceclient.grpc.generated.Empty;
import fr.tao.customerserviceclient.mapper.CustomerMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;

/**
 * This class represents a gRPC client for interacting with the CustomerService gRPC server.
 * 
 * It provides methods for fetching customer data from the server.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Slf4j
public class GrpcCustomerServiceClient {
	
	/**
	 * The gRPC blocking stub for customer service.
	 */
	@GrpcClient(value = "grpcCustomerClient")
	private CustomerServiceGrpc.CustomerServiceBlockingStub blockingStub;
	
	/**
     * Mapper for converting between Customer DTOs and Customer objects.
     */
	@Autowired
	private CustomerMapper customerMapper;

	/**
     * Retrieves a list of all customers from the gRPC server.
     *
     * @return A list of Customer objects.
     */
	public List<Customer> getAllCustomers() {
		log.info("GrpcCustomerServiceClient - Fetching all customers.");
		var response = blockingStub.getAllCustomers(Empty.newBuilder().build());
        return customerMapper.fromCustomerResponses(response.getCustomersList());

	}
	
	/**
     * Retrieves a customer by their unique identifier from the gRPC server.
     *
     * @param id The unique identifier of the customer.
     * @return A Customer object if found, or null if not found.
     */
	public Customer getCustomerById(Long id) {
		log.info("GrpcCustomerServiceClient - Fetching customer with ID: {}", id);
		var customerId = CustomerId.newBuilder().setId(id).build();
        return customerMapper.fromCustomerResponse(blockingStub.getCustomerById(customerId));
    }

}
