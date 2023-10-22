package fr.tao.customerserviceclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Represents a response object for customer data.
 * This class is used to deserialize JSON responses from an API endpoint.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse {
	
	/**
	 * Attribute to hold customer data
	 */
	private Data data;

}
