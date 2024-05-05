package fr.tao.customerserviceclient.service.rest;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.tao.customerserviceclient.model.Customer;

/**
 * This interface represents a Feign client for interacting with the Customer
 * API.
 *
 * It provides methods to make HTTP requests to retrieve customer information.
 *
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@FeignClient(value = "Customer-API-Client", url = "${api.base_url}")
public interface CustomerFeignClient {

    /**
     * Retrieves a list of all customers from the Customer API.
     *
     * @return A List of Customer objects containing information about all
     *         customers.
     *
     *         @GetMapping("${api.uri_customers}") Annotates this method to make an
     *         HTTP GET request to the URI specified in "${api.uri_customers}". The
     *         result is expected to be deserialized into a List of Customer
     *         objects.
     */
    @GetMapping("${api.uri_customers}")
    List<Customer> getAllCustomers();

	/**
	 * Retrieves customer information by their unique identifier.
	 *
	 * @param id The unique identifier of the customer to retrieve.
	 * @return A Customer object containing information about the requested
	 *         customer.
	 *
	 *         @GetMapping("${api.uri_customers}/{id}") Annotates this method to
	 *         make an HTTP GET request to the URI specified in
	 *         "${api.uri_customers}/{id}". The "{id}" part is replaced with the
	 *         actual value of the "id" parameter. The result is expected to be
	 *         deserialized into a Customer object.
	 */
	@GetMapping("${api.uri_customers}/{id}")
	Customer getCustomerById(@PathVariable("id") Long id);

}
