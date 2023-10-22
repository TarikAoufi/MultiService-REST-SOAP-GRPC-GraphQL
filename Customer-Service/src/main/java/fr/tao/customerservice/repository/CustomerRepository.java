package fr.tao.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.tao.customerservice.entity.Customer;

/**
 * This interface represents a repository for managing Customer entities. 
 * 
 * It extends the JpaRepository interface for basic CRUD operations and also defines 
 * a custom query method for finding customers by a case-insensitive partial match on their names.
 * 
 * @author T. Aoufi
 * @version 1.0
 * @since 21/10/2023
 */
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	/**
     * Find customers whose names contain the specified keyword, 
     * regardless of letter case.
     *
     * @param keyword The keyword to search for in customer names.
     * @return A list of customers matching the search criteria.
     */
	List<Customer> findByNameContainsIgnoreCase(String keyword);
}
