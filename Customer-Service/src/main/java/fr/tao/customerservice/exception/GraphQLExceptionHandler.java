package fr.tao.customerservice.exception;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Custom GraphQL exception handler to resolve specific exceptions to GraphQL errors.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Component
@Slf4j
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
	
    /**
     * Resolves the provided exception to a GraphQL error, based on its class name, 
     * and maps it accordingly.
     *
     * @param ex  The exception to be resolved.
     * @param env The DataFetchingEnvironment associated with the GraphQL operation.
     * @return A GraphQL error object if the exception is recognized, or null if it is not.
     */
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
	switch (ex.getClass().getSimpleName()) {
	    case "CustomerNotFoundException" :
	        log.error("Customer not found: {}", ex.getMessage());
	        return GraphqlErrorBuilder.newError()
	                .errorType(ErrorType.NOT_FOUND)
	                .message(ex.getMessage())
	                .path(env.getExecutionStepInfo().getPath())
	                .location(env.getField().getSourceLocation())
	                .build();
	    default: 
	        return null;
	}
    }		

}
