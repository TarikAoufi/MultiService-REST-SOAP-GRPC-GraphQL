package fr.tao.customerservice.service;

import java.util.List;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.exception.CustomerNotFoundException;
import jakarta.persistence.NoResultException;

/**
 * This interface defines the contract for managing customer information.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
public interface CustomerService {
	
    /**
     * Retrieves a list of all customers.
     *
     * @return A list of CustomerDto objects representing customers.
     * @throws Exception if there is an error during the operation.
     */
    public List<CustomerDto> getAllCustomer() throws Exception;
	
    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param customerId The unique identifier of the customer.
     * @return A CustomerDto object representing the customer.
     * @throws CustomerNotFoundException if the customer with the specified ID is not found.
     */
    public CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException;
	
    /**
     * Saves customer information.
     *
     * @param customerDto The CustomerDto object containing customer information to be saved.
     * @return The saved CustomerDto object.
     * @throws Exception if there is an error during the operation.
     */
    public CustomerDto saveCustomer(CustomerDto customerDto) throws Exception;	
	
    /**
     * Retrieves a list of customers whose names contain the specified keyword.
     *
     * @param name The keyword to search for in customer names.
     * @return A list of CustomerDto objects representing matching customers.
     * @throws NoResultException if no matching customers are found.
     */
    public List<CustomerDto> getCustomersByNameContains(String name) throws NoResultException;
	
    /**
     * Updates customer information by their unique identifier.
     *
     * @param customerId The unique identifier of the customer to be updated.
     * @param customerDto The CustomerDto object containing updated customer information.
     * @return The updated CustomerDto object.
     * @throws Exception if there is an error during the operation.
     */
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) throws Exception;
	
    /**
     * Deletes a customer by their unique identifier.
     *
     * @param customerId The unique identifier of the customer to be deleted.
     * @throws Exception if there is an error during the operation.
     */
    public void deleteCustomer(Long customerId) throws Exception;
	
}
