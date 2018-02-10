/**
 * 
 */
package by.neon.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.neon.journal.persistence.Student;

/**
 * @author Nikolay Moskal
 *
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
