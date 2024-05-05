package fr.tao.customerservice.exception;

import fr.tao.customerservice.util.MessageUtil;

/**
 * A custom runtime exception used to indicate that a customer 
 * with a specific ID is not found.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	
    /**
     * Constructs a new CustomerNotFoundException with the provided customer ID.
     *
     * @param id The ID of the customer that was not found.
     */
    public CustomerNotFoundException(Long id) {
        super(MessageUtil.CUSTOMER_BY_ID_NOT_FOUND + id);
    }
	
}
