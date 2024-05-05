package fr.tao.customerserviceclient.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response object for GraphQL queries.
 * This class is designed to encapsulate the data returned by a GraphQL query.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class GraphqlResponse {
	
    /**
     * A Map to store the data returned by the GraphQL query.
     */
    private Map<String, Object> data;
	
}
