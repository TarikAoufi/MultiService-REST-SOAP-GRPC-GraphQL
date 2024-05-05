package fr.tao.customerservice.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tao.customerservice.dto.CustomerDto;
import fr.tao.customerservice.exception.CustomerNotFoundException;
import fr.tao.customerservice.mapper.CustomerMapper;
import fr.tao.customerservice.repository.CustomerRepository;
import fr.tao.customerservice.util.MessageUtil;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * This class is an implementation of the CustomerService interface.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
    @NonNull private final CustomerRepository customerRepository;
    
    @NonNull private final CustomerMapper customerMapper;
    
    @NonNull private final Validator validator;
	
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> getAllCustomer() throws Exception {
	log.info(" #### Getting all customers #### ");
	var customers = customerRepository.findAll();
	if (customers.isEmpty()) {
	    log.warn("No customers found");
	    throw new NoResultException(MessageUtil.CUSTOMERS_NOT_FOUND);
	}
	log.info("Retrieved {} customers", customers.size());
	return customerMapper.toCustomerDtoList(customers);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto getCustomerById(Long customerId) throws CustomerNotFoundException {
	log.info(" #### Getting customer by ID: {} #### ", customerId);
	var customer = customerRepository.findById(customerId)
		.orElseThrow(() -> { 
	            log.warn("Customer with ID {} not found", customerId);
		    return new CustomerNotFoundException(customerId);				
		});
	log.info("Retrieved customer: {}", customer);
	return customerMapper.toDto(customer);
    }
	
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) throws Exception {
	log.info(" #### Saving new customer: {} #### ", customerDto);
	var violations = validator.validate(customerDto);
	if (!violations.isEmpty()) {
	    log.warn("Validation failed for customer: {}", customerDto);
            throw new ConstraintViolationException(violations);
        }
	var customer = customerMapper.toEntity(customerDto);
	customer = customerRepository.save(customer);
	log.info("Saved customer: {}", customer);
	return customerMapper.toDto(customer);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> getCustomersByNameContains(String name) throws NoResultException {
	log.info(" #### Getting customers by name contains: {} #### ", name);
	var customers = customerRepository.findByNameContainsIgnoreCase(name);
	if (customers.isEmpty()) {
	    log.warn("No customers found with name containing: {}", name);
	    throw new NoResultException(MessageUtil.CUSTOMERS_BY_NAME_NOT_FOUND + name);
	}
        log.info("Retrieved {} customers with name containing: {}", customers.size(), name);
	return customerMapper.toCustomerDtoList(customers);
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) throws Exception {
	log.info(" #### Updating customer with ID: {} - New data: {} #### ", customerId, customerDto);
	var customer = customerRepository.findById(customerId)
		.orElseThrow(() -> {
                    log.warn("Customer with ID {} not found for update", customerId);
                    return new CustomerNotFoundException(customerId);
                });
	customerMapper.updateCustomerFromDto(customerDto, customer);
	var violations = validator.validate(customer);
	if (!violations.isEmpty()) {
	    log.warn("Validation failed for updated customer: {}", customer);
            throw new ConstraintViolationException(violations);
        }
        customer = customerRepository.save(customer);
        log.info("Updated customer: {}", customer);
        return customerMapper.toDto(customer);
    }
	
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException  {
	log.info(" #### Deleting customer with ID: {} #### ", customerId);
	var customer = customerRepository.findById(customerId)
		.orElseThrow(() -> {
                    log.warn("Customer with ID {} not found for deletion", customerId);
                    return new CustomerNotFoundException(customerId);
                });
	customerRepository.delete(customer);	
	log.info("Deleted customer: {}", customer);
    }

}
