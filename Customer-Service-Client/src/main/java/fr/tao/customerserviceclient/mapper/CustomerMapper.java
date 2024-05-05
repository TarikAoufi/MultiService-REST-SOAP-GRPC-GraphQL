package fr.tao.customerserviceclient.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.tao.customerserviceclient.model.Customer;
import fr.tao.customerserviceclient.grpc.generated.CustomerResponse;
import fr.tao.customerserviceclient.soap.generated.CustomerDto;

/**
 * An interface for mapping between different representations of Customer objects.
 *
 * This interface uses MapStruct for automatic mapping implementation.
 *
 * @Mapper annotation is used to configure the mapping behavior, including the componentModel
 * which is set to "spring" for Spring integration, and unmappedTargetPolicy set to ReportingPolicy.IGNORE
 * to ignore unmapped properties.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
	
    /**
     * Singleton instance of the CustomerMapper, used for accessing mapping functions.
     */
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
    /**
     * Maps a CustomerDto object to a Customer object.
     *
     * @param customerDto The source CustomerDto to map from.
     * @return A mapped Customer object.
     */
    Customer fromCustomerDto(CustomerDto customerDto);
	
    /**
     * Maps a list of CustomerDto objects to a list of Customer objects.
     *
     * @param customerDtos The list of CustomerDtos to map from.
     * @return A list of mapped Customer objects.
     */
    List<Customer> fromCustomerDtoList(List<CustomerDto> customerDtos);
	
    /**
     * Maps a CustomerResponse object to a Customer object.
     *
     * @param customerResponse The source CustomerResponse to map from.
     * @return A mapped Customer object.
     */
    Customer fromCustomerResponse(CustomerResponse customerResponse);
	
    /**
     * Maps a list of CustomerResponse objects to a list of Customer objects.
     *
     * @param customerResponses The list of CustomerResponses to map from.
     * @return A list of mapped Customer objects.
     */
    List<Customer> fromCustomerResponses(List<CustomerResponse> customerResponses);

}
