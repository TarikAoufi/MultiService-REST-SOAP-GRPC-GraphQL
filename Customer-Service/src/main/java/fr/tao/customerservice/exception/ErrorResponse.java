package fr.tao.customerservice.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents an error response with the HTTP status code and a message.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Data @AllArgsConstructor
public class ErrorResponse {	
	
	/**
     * The HTTP status code associated with the error response.
     */
	private HttpStatus statusCode;
	
	/**
     * The message describing the error.
     */
    private String message;
}
