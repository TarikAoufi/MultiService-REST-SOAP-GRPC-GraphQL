package fr.tao.customerservice.api.soap;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.service.CustomerService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represents a SOAP web service for managing customer data.
 * 
 * It provides methods for retrieving a list of customers, fetching a customer by ID,
 * and saving customer information.
 *
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Component
@RequiredArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {
	
	/**
     * The service responsible for handling customer data operations.
     */
	@NonNull private final CustomerService customerService;
	
	/**
     * Retrieves a list of all customers.
     *
     * @return A list of CustomerDto objects representing all customers.
     * @throws Exception If an error occurs during the operation.
     */
	@WebMethod
	public List<CustomerDto> getAllCustomer() throws Exception {
		return customerService.getAllCustomer();
	}
	
	/**
     * Retrieves a customer by their unique identifier.
     *
     * @param customerId The identifier of the customer to retrieve.
     * @return A CustomerDto object representing the customer with the specified ID.
     */
	@WebMethod
	public CustomerDto customerById(@WebParam(name = "id") Long customerId) {
		return customerService.getCustomerById(customerId); 
	}
	
	/**
     * Saves a new customer's information.
     *
     * @param customerDto The CustomerDto object containing customer information to be saved.
     * @return The saved CustomerDto object.
     * @throws Exception If an error occurs during the operation.
     */
	@WebMethod
	public CustomerDto saveCustomer(@WebParam(name = "customer") CustomerDto customerDto) throws Exception {
		return customerService.saveCustomer(customerDto);
	}

}
