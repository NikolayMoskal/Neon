/**
 * UniversityRepository.java
 */
package by.neon.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.neon.journal.persistence.University;

/**
 * @author Nikolay Moskal
 *
 */
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
	/**
	 * Finds and returns the university by its name
	 * 
	 * @param name
	 *            the university name
	 * @return {@link University}
	 */
	@Query("SELECT u FROM University u WHERE u.name = :name")
	University findByName(@Param("name") String name);
}
