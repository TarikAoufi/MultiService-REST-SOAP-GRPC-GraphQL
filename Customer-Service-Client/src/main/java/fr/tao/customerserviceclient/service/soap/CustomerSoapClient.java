package fr.tao.customerserviceclient.service.soap;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.common.RemoteApiProperties;
import fr.tao.customerserviceclient.mapper.CustomerMapper;
import fr.tao.customerserviceclient.soap.generated.CustomerSoapService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class acts as a client for interacting with a SOAP-based customer service.
 * 
 * It provides methods to retrieve customer data from the remote API and map it to
 * a more usable format.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSoapClient {
	
	/**
     * Configuration properties for the remote API.
     */
	@NonNull 
	private final RemoteApiProperties remoteApiProperties;	
	
	/**
     * Service for making SOAP requests to the customer API.
     */
	@NonNull 
	private final CustomerSoapService customerSoapService;
	
	/**
     * Mapper for converting between Customer DTOs and Customer objects.
     */
	@NonNull 
	private final CustomerMapper customerMapper;
	
	/**
     * Retrieve a list of all customers from the remote API.
     *
     * @return A list of Customer objects representing the customers.
     * @throws Exception If there is an error during the SOAP request or mapping.
     */
	public List<Customer> getAllCustomers() throws Exception {
		log.info("CustomerSoapClient - Getting all customers from remote API");
		var customers = customerSoapService.getAllCustomer();
		return customerMapper.fromCustomerDtoList(customers);
	}
	
	/**
     * Retrieve a customer by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return The Customer object representing the customer with the given ID.
     */
	public Customer getCustomerById(Long id) {
		log.info("CustomerSoapClient - Getting customer by ID: {}", id);
		var customerDto = customerSoapService.customerById(id);
		return customerMapper.fromCustomerDto(customerDto);
	}
	

}
