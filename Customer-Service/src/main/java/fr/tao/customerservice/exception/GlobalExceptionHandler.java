package fr.tao.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.tao.customerservice.util.MessageUtil;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Custom global exception handler for handling various exceptions 
 * and returning appropriate error responses.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
     * Handles the CustomerNotFoundException and returns a ResponseEntity 
     * with a NOT FOUND status and a corresponding error response.
     *
     * @param ex The CustomerNotFoundException to handle.
     * @return ResponseEntity containing the error response for CustomerNotFoundException.
     */
	@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {			
		var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
		log.error("CustomerNotFoundException handled: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
	
	/**
     * Handles the NoResultException and returns a ResponseEntity 
     * with an OK status and a corresponding error response.
     *
     * @param ex The NoResultException to handle.
     * @return ResponseEntity containing the error response for NoResultException.
     */
	@ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> handleNoResultException(NoResultException ex) {		
		var errorResponse = new ErrorResponse(HttpStatus.OK, ex.getMessage());
		log.warn("NoResultException handled: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
    }
	
	/**
     * Handles the ConstraintViolationException and returns a ResponseEntity 
     * with a BAD REQUEST status and a validation error response.
     *
     * @param ex The ConstraintViolationException to handle.
     * @return ResponseEntity containing the error response for ConstraintViolationException.
     */
	@ExceptionHandler(ConstraintViolationException .class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException  ex) {			    
		var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation error: " + ex.getConstraintViolations());
		log.error("ConstraintViolationException handled: " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
	
	/**
     * Handles generic exceptions and returns a ResponseEntity 
     * with an INTERNAL SERVER ERROR status and a standard error response.
     *
     * @param ex The Exception to handle.
     * @return ResponseEntity containing the standard error response for generic exceptions.
     */
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {		
		var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, 
				MessageUtil.INTERNAL_SERVER_ERROR);
		log.error("Exception handled: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
