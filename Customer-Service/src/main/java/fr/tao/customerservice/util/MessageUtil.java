package fr.tao.customerservice.util;


/**
 * This is a utility class that provides various constants and messages 
 * used throughout the application.
 * 
 * It includes messages for GraphQL queries, model validation, service layer exceptions, 
 * API documentation, and endpoint URIs for the Customer Service.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
public final class MessageUtil {

	// Messages for GraphQL queries
	public static final String GET_ALL_CUSTOMER_GQL_QUERY = "{ \"query\": \"{ allCustomer { id name email } }\" }";

	public static final String GET_CUSTOMER_BY_ID_GQL_QUERY = "{ \"query\": \"{ customerById(id: %s) { id name email } }\" }";
	
	public static final String GET_CUSTOMER_BY_NAME_GQL_QUERY = "{ customerByName(name: \"%s\") { id name email } }";
	
	public static final String SAVE_CUSTOMER_GQL_MUTATION = "mutation { saveCustomer(customer: { name: \"%s\", email: \"%s\" }) { id name email } }";
	
	// Messages for model validator 
	public static final String CUSTOMER_NAME_NOT_NULL_EMPTY = "Name cannot be null or empty.";
	public static final String CUSTOMER_NAME_NOT_VALID_SIZE = "Name must be between 2 and 20 characters.";
	public static final String CUSTOMER_NAME_REGEXP = "^[a-zA-Z]+$";
	public static final String CUSTOMER_NAME_NOT_VALID_PATTERN = "Name should only contain alphabetic characters.";
	public static final String CUSTOMER_EMAIL_NOT_NULL_EMPTY = "Email cannot be null or empty.";
	public static final String CUSTOMER_EMAIL_NOT_VALID_SIZE = "Email cannot exceed 30 characters.";
	public static final String CUSTOMER_EMAIL_REGEXP = "^(?=.{2,30}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
	public static final String CUSTOMER_EMAIL_NOT_VALID_PATTERN = "Email should be a valid email address";
	
	// Exception messages for service layer
	public static final String CUSTOMER_BY_ID_NOT_FOUND = "Customer not found with ID: ";
	public static final String CUSTOMER_SUCCESS_DELETE = "Customer successfully deleted.";
	public static final String CUSTOMERS_NOT_FOUND = "No customers found.";
	public static final String CUSTOMERS_BY_NAME_NOT_FOUND = "No customers found with the given name: ";
    public static final String INVALID_REQUEST = "Invalid request.";
    public static final String INTERNAL_SERVER_ERROR = "An error has occurred.";
    
    // API messages
    public static final String API_REST_DOC_TITLE = "The Customer Service REST-API.";
    public static final String API_REST_DOC_DESCRIPTION = "This is a documentation for our customer service REST-API.";
    
    // Endpoint  
    public static final String SOAP_SERVICE_URI = "/CustomerSoapService";
	
	
	// Private constructor to prevent instantiation
    private MessageUtil() {
        throw new AssertionError("Utility class - do not instantiate");
    }

}
