package fr.tao.customerservice.configuration;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.tao.customerservice.api.soap.CustomerSoapService;
import fr.tao.customerservice.util.MessageUtil;

/**
 * Configuration class for SOAP services. It defines a bean to create 
 * and configure a SOAP endpoint for the CustomerSoapService, 
 * which is published at the specified URI.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Configuration
public class SoapConfig {
	
    /**
     * Creates and configures a SOAP endpoint for the CustomerSoapService.
     *
     * @param bus The Spring Bus instance used for managing endpoints.
     * @param customerSoapService The CustomerSoapService instance to be exposed.
     * @return An EndpointImpl instance configured for the CustomerSoapService.
     */
    @Bean
    EndpointImpl customerSoapEndpoint(Bus bus, CustomerSoapService customerSoapService) {
        EndpointImpl endpoint = new EndpointImpl(bus, customerSoapService);
        endpoint.publish(MessageUtil.SOAP_SERVICE_URI); 
        return endpoint;
    }

}
