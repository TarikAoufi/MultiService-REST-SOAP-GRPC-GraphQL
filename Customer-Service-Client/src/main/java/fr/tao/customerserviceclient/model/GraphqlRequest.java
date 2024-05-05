package fr.tao.customerserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a GraphQL request with a query string.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023 
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class GraphqlRequest {
	
    /**
     * The GraphQL query string
     */
    private String query;

}
