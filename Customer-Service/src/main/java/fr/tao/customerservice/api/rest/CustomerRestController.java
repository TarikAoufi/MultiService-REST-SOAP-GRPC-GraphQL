package fr.tao.customerservice.api.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.exception.CustomerNotFoundException;
import fr.tao.customerservice.service.CustomerService;
import fr.tao.customerservice.util.MessageUtil;
import jakarta.persistence.NoResultException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a RESTful controller for managing customer data. 
 * 
 * It handles various operations related to customers, such as retrieving, creating, 
 * updating, and deleting customers.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@RestController
@CrossOrigin("*")
@RequestMapping(produces = "application/json", value = "/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerRestController {
	
    /**
     * A required constructor-based injection of the CustomerService.
     */
    @NonNull private final CustomerService customerService;
	
    /**
     * Get a list of all customers.
     *
     * @return ResponseEntity<List<CustomerDto>: A response entity containing a list of customer data in JSON format.
     * @throws Exception if there is an error while processing the request.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() throws Exception {
	log.info("getCustomers - REST request: Getting all customers");
	var customers = customerService.getAllCustomer();
	return ResponseEntity.ok(customers); 	
    }
	
    /**
     * Get a customer by their ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return ResponseEntity<CustomerDto>: A response entity containing customer data in JSON format.
     * @throws CustomerNotFoundException if the specified customer is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
	log.info("getCustomerById - REST request: Getting customer by ID: {}", id);
	var customer = customerService.getCustomerById(id);
	return ResponseEntity.ok(customer);
    }
	
    /**
     * Search for customers by name.
     *
     * @param name The name or part of the name to search for.
     * @return ResponseEntity<List<CustomerDto>: A response entity containing a list of matching customer data in JSON format.
     * @throws NoResultException if no matching customers are found.
     */
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> searchCustomersByName(@RequestParam String name) throws NoResultException {	
	log.info("searchCustomersByName - REST request: Getting customers by name contains: {}", name);
	var customerDtos = customerService.getCustomersByNameContains(name);
	return ResponseEntity.ok().body(customerDtos);
    }
	
    /**
     * Create a new customer.
     *
     * @param customerDto The customer data to be created.
     * @return ResponseEntity<CustomerDto>: A response entity containing the newly created customer data in JSON format.
     * @throws Exception if there is an error while processing the request.
     */
    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) throws Exception {	
	log.info("saveCustomer - REST request: Saving new customer: {}", customerDto);
	var createdCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
	
    /**
     * Update an existing customer.
     *
     * @param customerId The ID of the customer to update.
     * @param customerDto The updated customer data.
     * @return ResponseEntity<CustomerDto>: A response entity containing the updated customer data in JSON format.
     * @throws Exception if there is an error while processing the request.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
    	    @PathVariable("id") Long customerId, 
    	    @RequestBody CustomerDto customerDto) throws Exception {
	log.info("updateCustomer - REST request: Updating customer with ID: {} - New data: {}", customerId, customerDto);
        var updatedCustomer = customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.ok().body(updatedCustomer);
    }
	
    /**
     * Delete a customer by their ID.
     *
     * @param customerId The ID of the customer to delete.
     * @return ResponseEntity<String>: A response entity with a message indicating the success of the deletion.
     * @throws Exception if there is an error while processing the request.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) throws Exception {
	log.info("deleteCustomer - REST request: Deleting customer with ID: {}", customerId);
	customerService.deleteCustomer(customerId);		
	return ResponseEntity.ok().body(MessageUtil.CUSTOMER_SUCCESS_DELETE);
    }
	
}
