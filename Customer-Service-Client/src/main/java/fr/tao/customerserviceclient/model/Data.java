package fr.tao.customerserviceclient.model;

import java.util.List;

/**
 * This class represents a data container for managing customer data.
 * It contains attributes and methods for accessing and modifying customer data.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023 
 */
public class Data {
    
    // List to store all customers
    private List<Customer> allCustomer;
    
    // Customer object to store a single customer by ID
    private Customer customerById;

    /**
     * Gets the list of all customers.
     * @return A list of all customer objects.
     */
    public List<Customer> getAllCustomer() {
        return allCustomer;
    }

    /**
     * Sets the list of all customers.
     * @param allCustomer The list of customer objects to be set.
     */
    public void setAllCustomer(List<Customer> allCustomer) {
        this.allCustomer = allCustomer;
    }

    /**
     * Gets a customer object by their ID.
     * @return The customer object with the specified ID.
     */
    public Customer getCustomerById() {
        return customerById;
    }

    /**
     * Sets a customer object by their ID.
     * @param customerById The customer object to be set.
     */
    public void setCustomerById(Customer customerById) {
        this.customerById = customerById;
    }
}
