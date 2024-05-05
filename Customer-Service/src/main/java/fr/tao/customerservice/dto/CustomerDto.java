package fr.tao.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A simple data transfer object (DTO) representing customer information.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerDto {
	
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
