package fr.tao.customerservice.api.grpc;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.exception.CustomerNotFoundException;
import fr.tao.customerservice.grpc.generated.CustomerId;
import fr.tao.customerservice.grpc.generated.CustomerList;
import fr.tao.customerservice.grpc.generated.CustomerRequest;
import fr.tao.customerservice.grpc.generated.CustomerResponse;
import fr.tao.customerservice.grpc.generated.CustomerServiceGrpc;
import fr.tao.customerservice.grpc.generated.Empty;
import fr.tao.customerservice.mapper.CustomerMapper;
import fr.tao.customerservice.service.CustomerService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * gRPC service implementation for managing customer operations.
 * This class provides gRPC endpoints for retrieving, creating, and interacting with customers.
 * It communicates with the CustomerService and utilizes a CustomerMapper for data transformation.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@GrpcService
@RequiredArgsConstructor
@Slf4j
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
	
    /**
     * The service responsible for managing customer data.
     */
    @NonNull private final CustomerService customerService;
    /**
     * A mapper for converting between customer DTOs and Protos.
     */
    @NonNull private final CustomerMapper customerMapper;
	
    /**
     * Retrieves all customers and sends the response to the client.
     *
     * @param request          Empty request from the client.
     * @param responseObserver StreamObserver for sending the response.
     */
    @Override
    public void getAllCustomers(Empty request, StreamObserver<CustomerList> responseObserver) {
	try {
	    log.info("Handling getAllCustomers via gRPC request.");
	    var customers = customerService.getAllCustomer();
			
	    // Converting customers to Protos
	    var customerProtos = customers.stream()
	            .map(customerMapper::toCustomerProto)
	            .toList();
			
	    var response = CustomerList.newBuilder()
	            .addAllCustomers(customerProtos)
	            .build();
			
	    // Sending response to client
	    responseObserver.onNext(response);
            responseObserver.onCompleted();
	        
	    log.info("getAllCustomers - gRPC request: Successfully retrieved all customers.");
	} catch (Exception e) {
            log.error("getAllCustomers - gRPC request: Error while retrieving all customers.", e);
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());      
	}         
    }
	
    /**
     * Retrieves a customer by ID and sends the response to the client.
     *
     * @param request          CustomerId request specifying the customer ID.
     * @param responseObserver StreamObserver for sending the response.
     */
    @Override
    public void getCustomerById(CustomerId request, StreamObserver<CustomerResponse> responseObserver) {
	try {
            log.info("Handling getCustomerById, via gRPC request, for ID: {}", request.getId());
            var customerDto = customerService.getCustomerById(Long.valueOf(request.getId()));
            responseObserver.onNext(customerMapper.toCustomerProto(customerDto));
            responseObserver.onCompleted();
            log.info("getCustomerById - gRPC request: Successfully retrieved customer for ID: {}", request.getId());
        } catch (CustomerNotFoundException e) {
            log.error("getCustomerById - gRPC request: Customer with ID {} not found.", request.getId());
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        }
    }
	
    /**
     * Creates a new customer and sends the response to the client.
     *
     * @param request          CustomerRequest with customer information.
     * @param responseObserver StreamObserver for sending the response.
     */
    @Override
    public void createCustomer(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
	try {
	    log.info("Handling createCustomer via gRPC request.");
            CustomerDto customerDto = customerService.saveCustomer(customerMapper.fromCustomerRequest(request));
            responseObserver.onNext(customerMapper.toCustomerProto(customerDto));
            responseObserver.onCompleted();
            log.info("createCustomer - gRPC request: Successfully created a new customer.");
        } catch (Exception e) {
            log.error("createCustomer - gRPC request: Error while creating a new customer.", e);
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }
	
}
