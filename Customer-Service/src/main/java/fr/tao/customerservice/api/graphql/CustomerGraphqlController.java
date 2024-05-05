package fr.tao.customerservice.api.graphql;


import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.service.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 * GraphQL controller for managing customer data.
 * It provides various GraphQL queries and mutations to interact with customer information.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Controller
@RequiredArgsConstructor
public class CustomerGraphqlController {
	
    /**
     * A required constructor-based injection of the CustomerService.
     */
    @NonNull private final CustomerService customerService;
	
    /**
     * Retrieves a list of all customers.
     * 
     * @return A list of CustomerDto objects representing all customers.
     * @throws Exception if there is an error during the operation.
     */
    @QueryMapping
    public List<CustomerDto> allCustomer() throws Exception {
	return customerService.getAllCustomer();
    }
	
    /**
     * Retrieves a list of customers by their name.
     * 
     * @param name The name to search for within customer names.
     * @return A list of CustomerDto objects representing customers with names containing the given string.
     * @throws Exception if there is an error during the operation.
     */
    @QueryMapping
    public List<CustomerDto> customerByName(@Argument String name) throws Exception {
	return customerService.getCustomersByNameContains(name);
    }
	
    /**
     * Retrieves a customer by their ID.
     * 
     * @param id The ID of the customer to retrieve.
     * @return A CustomerDto object representing the customer with the specified ID.
     * @throws Exception if there is an error during the operation.
     */
    @QueryMapping
    public CustomerDto customerById(@Argument Long id) throws Exception {
	return customerService.getCustomerById(id);
    }
	
    /**
     * Saves a new customer.
     * 
     * @param customer The CustomerDto object to be saved.
     * @return A CustomerDto object representing the saved customer.
     * @throws Exception if there is an error during the operation.
     */
    @MutationMapping
    public CustomerDto saveCustomer(@Argument CustomerDto customer) throws Exception {
	return customerService.saveCustomer(customer);
    }
}
