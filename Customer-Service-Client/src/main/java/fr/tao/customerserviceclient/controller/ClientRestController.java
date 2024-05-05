package fr.tao.customerserviceclient.controller;

import java.util.List;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.service.graphql.CustomerGraphQLClient;
import fr.tao.customerserviceclient.service.grpc.GrpcCustomerServiceClient;
import fr.tao.customerserviceclient.service.rest.CustomerFeignClient;
import fr.tao.customerserviceclient.service.rest.CustomerRestTemplateClient;
import fr.tao.customerserviceclient.service.rest.CustomerWebClient;
import fr.tao.customerserviceclient.service.soap.CustomerSoapClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class represents a RESTful API controller for managing customer data through various client implementations.
 * 
 * It provides endpoints for retrieving customer information using different client technologies.
 *
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@RestController
@RequestMapping(produces = "application/json", value = "/customer-client")
@RequiredArgsConstructor
public class ClientRestController {
	
    @NonNull private final CustomerRestTemplateClient customerRestTemplateClient;
    @NonNull private final CustomerWebClient customerWebClient;
    @NonNull private final CustomerFeignClient customerFeignClient;
    @NonNull private final HttpGraphQlClient httpGraphQlClient;
    @NonNull private final CustomerGraphQLClient customerGraphQLClient;
    @NonNull private final CustomerSoapClient customerSoapClient;
    @NonNull private final GrpcCustomerServiceClient grpcCustomerServiceClient;


    // =================== REST Client by RestTemplate 
	
    /**
     * Retrieve all customers using RESTful client implemented with RestTemplate.
     * 
     * @return A list of Customer objects.
     */
    @GetMapping("${rest.template.uri_customers}")
    public List<Customer> getAllCustomersUsingRestTemplate() {				
	return customerRestTemplateClient.getAllCustomers();		
    } 
	
    /**
     * Retrieve a customer by their ID using RESTful client implemented with RestTemplate.
     * 
     * @param id The ID of the customer to retrieve.
     * @return The Customer object with the specified ID.
     */
    @GetMapping("${rest.template.uri_customerById}")
    public Customer customerByIdUsingRestTemplate(@PathVariable Long id) {
	return customerRestTemplateClient.getCustomerById(id);
    }

    // =================== REST Client by WebClient
	
    /**
     * Retrieve all customers using RESTful client implemented with WebClient.
     * 
     * @return A Flux of Customer objects.
     */
    @GetMapping("${rest.webclient.uri_customers}")
    public Flux<Customer> getAllCustomersUsingWebClient() {
	return customerWebClient.getAllCustomers();
    }
	
    /**
     * Retrieve a customer by their ID using RESTful client implemented with WebClient.
     * 
     * @param id The ID of the customer to retrieve.
     * @return A Mono containing the Customer object with the specified ID.
     */
    @GetMapping("${rest.webclient.uri_customerById}")
    public Mono<Customer> customerByIdUsingWebClient(@PathVariable Long id) {
        return customerWebClient.getCustomerById(id);
    }
	
    // =================== REST Client by OpenFeign 
	
    /**
     * Retrieves all customers using the REST client implemented with OpenFeign.
     *
     * @return A list of customers.
     */
    @GetMapping("${rest.feign.uri_customers}")
    public List<Customer> getAllCustomersUsingFeign() {
	return customerFeignClient.getAllCustomers();
    }
	
    /**
     * Retrieves a customer by their ID using the REST client implemented with OpenFeign.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    @GetMapping("${rest.feign.uri_customerById}")
    Customer getCustomerByIdUsingFeign(@PathVariable Long id) {
	return customerFeignClient.getCustomerById(id);
    }
	
    // =================== GraphQl Client By HttpGraphQlClient 
	
    /**
     * Retrieves all customers using the GraphQL client implemented with HttpGraphQlClient.
     *
     * @return A Mono containing a list of customers.
     */
    @GetMapping("${graphql.uri1_customers}")
    public Mono<List<Customer>> getAllCustomersGQLHttpGQl() {
 	return customerGraphQLClient.getAllCustomersGQLHttpGQl();
    }
	
    /**
     * Retrieves a customer by their ID using the GraphQL client implemented with HttpGraphQlClient.
     *
     * @param id The ID of the customer to retrieve.
     * @return A Mono containing the customer with the specified ID.
     */
    @GetMapping("${graphql.uri1_customerById}")
    public Mono<Customer> getCustomerByIdGQLHttpGQl(@PathVariable Long id) {
 	return customerGraphQLClient.getCustomerByIdGQLHttpGQl(id);
    }
	
    // =================== GraphQl Client By WebClient
	
    /**
     * Retrieves all customers using the GraphQL client implemented with WebClient.
     *
     * @return A list of customers.
     * @throws Exception if an error occurs during the retrieval.
     */
    @GetMapping("${graphql.uri2_customers}")
    public List<Customer> getAllCustomersGQLWebClient() throws Exception {
	return  customerGraphQLClient.getAllCustomersGQLWebClient();
    }
	
    /**
     * Retrieves a customer by their ID using the GraphQL client implemented with WebClient.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     * @throws Exception if an error occurs during the retrieval.
     */
    @GetMapping("${graphql.uri2_customerById}")
    public Customer getCustomerByIdGQLWebClient(@PathVariable Long id) throws Exception {
	return customerGraphQLClient.getCustomerByIdGQLWebClient(id);
    }	
	
    // =================== SOAP Client By jaxws and cxf-codegen-plugin
	
    /**
     * Retrieves all customers using the SOAP client implemented with jaxws and cxf-codegen-plugin.
     *
     * @return A list of customers.
     * @throws Exception if an error occurs during the retrieval.
     */
    @GetMapping("${soap.uri_customers}")
    public List<Customer> getAllCustomersSOAP() throws Exception {
	return customerSoapClient.getAllCustomers();		
    }
	
    /**
     * Retrieves a customer by their ID using the SOAP client implemented with jaxws and cxf-codegen-plugin.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    @GetMapping("${soap.uri_customerById}")
    public Customer getCustomerByIdSOAP(@PathVariable Long id) {
	return customerSoapClient.getCustomerById(id);		
    }
	
    // ===================  GRPC Client By protoc-jar-maven-plugin 
	
    /**
     * Retrieve all customers using GRPC client implemented with protoc-jar-maven-plugin.
     * 
     * @return A list of Customer objects.
     */
    @GetMapping("${grpc.uri_customers}")
    public List<Customer> getAllCustomersGRPC() {
	return grpcCustomerServiceClient.getAllCustomers();
    }
	
    /**
     * Retrieve a customer by their ID using GRPC client implemented with protoc-jar-maven-plugin.
     * 
     * @param id The ID of the customer to retrieve.
     * @return The Customer object with the specified ID.
     */
    @GetMapping("${grpc.uri_customerById}")
    public Customer getCustomerByIdGRPC(@PathVariable Long id) {
	return grpcCustomerServiceClient.getCustomerById(id);		
    }

}
