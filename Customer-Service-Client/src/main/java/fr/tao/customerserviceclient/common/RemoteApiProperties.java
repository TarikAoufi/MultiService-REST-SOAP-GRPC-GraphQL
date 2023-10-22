package fr.tao.customerserviceclient.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

/**
 * Configuration class for Remote API properties.
 * This class manages the configuration properties for remote API connections.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Getter
@Configuration
@PropertySource("classpath:remote_api.properties")
public class RemoteApiProperties {
	
	@Value("${api.base_url}")
	private String baseUrl; // Base URL for the remote API.
	
	@Value("${api.uri_customers}")
	private String uriCustomers; // URI for accessing customers.
	
	@Value("${api.uri_customerById}")
	private String uriCustomerById; // URI for accessing a specific customer.
	
	@Value("${graphql.uri}")
	private String uriGraphql; // URI for GraphQL endpoint.
	
	@Value("${soap.url}")
	private String soapUrl; // URL for SOAP service.
	
	/**
     * Constructs and returns the full URL for the remote API by appending the provided URI to the base URL.
     *
     * @param uri The URI to be appended to the base URL.
     * @return The full remote API URL.
     */
	public String getRemoteApiUrl(String uri) {
		return this.getBaseUrl() + uri;
	}

}
