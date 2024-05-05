package fr.tao.customerserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Customer class represents a customer entity with basic information.
 * It includes attributes such as ID, name, and email.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
    /**
     * The unique identifier for the customer.
     */
    private Long id;

    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The email address of the customer.
     */
    private String email;

}
