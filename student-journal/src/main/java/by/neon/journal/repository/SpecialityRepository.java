/**
 * 
 */
package by.neon.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.neon.journal.persistence.Speciality;

/**
 * @author Nikolay Moskal
 *
 */
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

}
