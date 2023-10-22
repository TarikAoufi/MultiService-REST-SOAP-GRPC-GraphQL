package fr.tao.customerservice.entity;

import fr.tao.customerservice.util.MessageUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Customer entity in the system. 
 * It is used to store customer information,
 * including their unique identifier, name, and email address.

 * This class is designed to store customer information 
 * with validation constraints on name and email.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer {
	/**
     * The unique identifier for the customer.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
     * The name of the customer.
     * Constraints:
     * - Must not be null or empty.
     * - Must be between 2 and 20 characters in length.
     * - Must match the specified regular expression pattern.
     */
	@NotEmpty(message = MessageUtil.CUSTOMER_NAME_NOT_NULL_EMPTY)
    @Size(min = 2, max = 20, message = MessageUtil.CUSTOMER_NAME_NOT_VALID_SIZE)
	@Pattern(regexp = MessageUtil.CUSTOMER_NAME_REGEXP, message = MessageUtil.CUSTOMER_NAME_NOT_VALID_PATTERN)
	private String name;
	
	/**
     * The email address of the customer.
     * Constraints:
     * - Must not be null or empty.
     * - Must be a maximum of 30 characters in length.
     * - Must match the specified regular expression pattern.
     */
	@NotEmpty(message = MessageUtil.CUSTOMER_EMAIL_NOT_NULL_EMPTY)
    @Size(max =30, message = MessageUtil.CUSTOMER_EMAIL_NOT_VALID_SIZE)
    @Pattern(regexp = MessageUtil.CUSTOMER_EMAIL_REGEXP, message = MessageUtil.CUSTOMER_EMAIL_NOT_VALID_PATTERN)
	private String email;

}
