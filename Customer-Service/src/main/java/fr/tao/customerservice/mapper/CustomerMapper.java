package fr.tao.customerservice.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.entity.Customer;
import fr.tao.customerservice.grpc.generated.CustomerRequest;
import fr.tao.customerservice.grpc.generated.CustomerResponse;

/**
 * This interface defines a MapStruct-based mapper for converting between Customer 
 * and CustomerDto objects.
 * 
 * It provides methods for mapping, updating, and converting lists of these objects.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
	
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
    /**
     * Converts a Customer entity to a CustomerDto.
     *
     * @param customer The Customer entity to be converted.
     * @return The corresponding CustomerDto.
     */
    CustomerDto toDto(Customer customer);
	
    /**
     * Converts a CustomerDto to a Customer entity.
     *
     * @param customerDto The CustomerDto to be converted.
     * @return The corresponding Customer entity.
     */
    Customer toEntity(CustomerDto customerDto);
    
    /**
     * Updates a Customer entity from a CustomerDto, with properties mapping 
     * based on annotations.
     *
     * @param customerDto  The CustomerDto containing updated data.
     * @param customer     The target Customer entity to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);
    
    /**
     * Converts a list of Customer entities to a list of CustomerDto objects.
     *
     * @param customers The list of Customer entities to be converted.
     * @return The corresponding list of CustomerDto objects.
     */
    List<CustomerDto> toCustomerDtoList(List<Customer> customers);
    
    /**
     * Converts a list of CustomerDto objects to a list of Customer entities.
     *
     * @param customerDtos The list of CustomerDto objects to be converted.
     * @return The corresponding list of Customer entities.
     */
    List<Customer> toCustomerList(List<CustomerDto> customerDtos);
    
    /**
     * Converts a CustomerDto to a CustomerResponse protocol buffer message.
     *
     * @param customerDto The CustomerDto to be converted to a CustomerResponse.
     * @return The corresponding CustomerResponse message.
     */
    CustomerResponse toCustomerProto(CustomerDto customerDto);
    
    /**
     * Converts a CustomerRequest to a CustomerDto.
     *
     * @param request The CustomerRequest to be converted.
     * @return The corresponding CustomerDto.
     */
    CustomerDto fromCustomerRequest(CustomerRequest request);
    
    /**
     * Converts a list of CustomerResponse protocol buffer messages 
     * to a list of CustomerDto objects.
     *
     * @param customerResponses The list of CustomerResponse messages to be converted.
     * @return The corresponding list of CustomerDto objects.
     */
    List<CustomerDto> fromCustomerResponses(List<CustomerResponse> customerResponses);

}
