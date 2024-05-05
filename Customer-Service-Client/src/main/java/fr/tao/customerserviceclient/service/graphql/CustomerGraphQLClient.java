package fr.tao.customerserviceclient.service.graphql;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.model.GraphqlRequest;
import fr.tao.customerserviceclient.model.GraphqlResponse;
import fr.tao.customerserviceclient.common.RemoteApiProperties;
import fr.tao.customerserviceclient.util.MessageUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * This class represents a GraphQL client for interacting with customer-related services.
 *
 * It provides methods for querying customer data using both HTTP and WebClient.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerGraphQLClient {
	
	
    /**
     * Configuration properties for the remote API.	
     */
    @NonNull 
    private final RemoteApiProperties remoteApiProperties;	
    
    /**
     * HTTP-based GraphQL client.
     */
    @NonNull 
    private final HttpGraphQlClient httpGraphQlClient;
    
    /**
     * WebClient for making GraphQL requests.
     */
    @NonNull 
    private final WebClient webClient;
    
    
    /**
     * Retrieves a list of all customers using HTTP-based GraphQL.
     *
     * @return A Mono representing a list of Customer objects.
     */
    public Mono<List<Customer>> getAllCustomersGQLHttpGQl() {
    	log.info("Fetching all customers using HTTP GraphQL client.");
	return httpGraphQlClient
		.document(MessageUtil.GET_ALL_CUSTOMER_QUERY)
		.retrieve(MessageUtil.ALL_CUSTOMER_SERVICE_NAME)
		.toEntityList(Customer.class);
    }
    
    /**
     * Retrieves a customer by their ID using HTTP-based GraphQL.
     *
     * @param id The ID of the customer to retrieve.
     * @return A Mono representing a Customer object.
     */
    public Mono<Customer> getCustomerByIdGQLHttpGQl(Long id) {
    	log.info("Fetching customer by ID {} using HTTP GraphQL client.", id);
	return httpGraphQlClient
		.document(MessageUtil.GET_CUSTOMER_BY_ID_QUERY.formatted(id))
		.variable("id", id)
		.retrieve(MessageUtil.CUSTOMER_BY_ID_SERVICE_NAME)
		.toEntity(Customer.class);
    }
    
    /**
     * Retrieves a list of all customers using WebClient-based GraphQL.
     *
     * @return A list of Customer objects.
     * @throws Exception if an error occurs during the GraphQL query execution.
     */
    public List<Customer> getAllCustomersGQLWebClient() throws Exception {
	log.info("Fetching all customers using WebClient.");
	String query = MessageUtil.GET_ALL_CUSTOMER_QUERY;
	var result = executeGraphQLQuery(query, MessageUtil.ALL_CUSTOMER_SERVICE_NAME);		
	var typeToken = new TypeToken<List<Customer>>() {};
		
	return result != null
	        ? new Gson().fromJson(result.toString(), typeToken.getType())
	        : Collections.emptyList();
    }
	
    /**
     * Retrieves a customer by their ID using WebClient-based GraphQL.
     *
     * @param id The ID of the customer to retrieve.
     * @return A Customer object.
     * @throws Exception if an error occurs during the GraphQL query execution.
     */
    public Customer getCustomerByIdGQLWebClient(Long id) throws Exception {
	log.info("Fetching customer by ID {} using WebClient.", id);
	String query = MessageUtil.GET_CUSTOMER_BY_ID_QUERY.formatted(id);
	var result = executeGraphQLQuery(query, MessageUtil.CUSTOMER_BY_ID_SERVICE_NAME);
		
	return Optional.ofNullable(result)
	        .map(json -> new Gson().fromJson(json.toString(), Customer.class))
	        .orElse(null);
    }
    
    /**
     * Executes a GraphQL query using the WebClient.
     *
     * @param query The GraphQL query to execute.
     * @param dataPath The path to the data in the GraphQL response.
     * @return The GraphQL response data.
     */
    private Object executeGraphQLQuery(String query, String dataPath) {
    	log.info("Executing GraphQL query using WebClient.");
        return webClient.post()
            .uri(remoteApiProperties.getUriGraphql()) 
            .bodyValue(new GraphqlRequest(query))
            .retrieve()
            .bodyToMono(GraphqlResponse.class)
            .map(response -> response.getData().get(dataPath))
            .block();
    }

}
