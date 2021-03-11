package it.eng.decido.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.eng.decido.domain.Employee;

/**
 * Spring Data MongoDB repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
	// See https://www.devglan.com/spring-boot/spring-data-mongodb-queries
	// See https://github.com/bolcom/spring-data-mongodb-encrypt
	
	@Query("{'first_name' : ?0}")
	Optional<Employee> searchFirstName(String firstName);
	
	Optional<Employee> findByFirstName(String firstName);
}
