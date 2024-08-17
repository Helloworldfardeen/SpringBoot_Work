package com.training.cdac.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.cdac.pms.model.Product;

/**
 * 
 * @author rajgs JPA Repository is mainly used for managing the data in a Spring
 *         Boot Application. JpaRepository is particularly a JPA specific
 *         extension for Repository. Jpa Repository contains the APIs for basic
 *         CRUD operations, the APIS for pagination, and the APIs for sorting.
 *         This Layer interacts with Database
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	// Long is data type of id field of Product class
	/*
	 * This interface has save(),findAll(),findById(),deleteById(),count() etc..
	 * inbuilt methods of jpa repository for various database operations. This
	 * interface will be implemented by class automatically
	 */

	/*
	 * @Query specifies that you're providing a custom JPQL query. We use the
	 * REPLACE function to remove spaces both from the p.name field and from the
	 * provided :name, making them both single continuous strings with no spaces.
	 * JPQL query that selects products where the lowercase name contains the
	 * lowercase input name with wildcards.
	 * 
	 * @Param("name") is used to bind the name parameter from the method signature
	 * to the :name placeholder in the query
	 */
//	@Query("SELECT p FROM Product p WHERE p.name LIKE :pattern")
//	List<Product> findProductsWithFull(@Param("pattern") String pattern);
	@Query("SELECT p FROM Product p WHERE LOWER(REPLACE(p.name, ' ', '')) LIKE "
			+ "LOWER(CONCAT(REPLACE(:name, ' ', ''), '%'))")
	List<Product> findProductsByNameContainingIgnoreCase(@Param("name") String name);

}
