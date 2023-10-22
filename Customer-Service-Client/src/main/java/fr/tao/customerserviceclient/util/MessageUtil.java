package fr.tao.customerserviceclient.util;

/**
 * This is a utility class for defining GraphQL query messages and related constants.
 * 
 * It provides predefined GraphQL query strings and service names for customer-related queries.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
public final class MessageUtil {
	
	// Messages for GraphQL queries
	public static final String GET_ALL_CUSTOMER_QUERY = """
			query {
				allCustomer {
			    	id name email
			  	}
			}
			""";
	
	public static final String GET_CUSTOMER_BY_ID_QUERY = """
			query {
				customerById(id: %s) {
					id name email
			    }
			}
			""";
	
	// Constants for service names
	public static final String ALL_CUSTOMER_SERVICE_NAME = "allCustomer";
	public static final String CUSTOMER_BY_ID_SERVICE_NAME = "customerById";
	
	// Private constructor to prevent instantiation
    private MessageUtil() {
        throw new AssertionError("This class should not be instantiated.");
    }
}
