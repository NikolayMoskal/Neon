/**
 * 
 */
package by.neon.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.neon.journal.persistence.Group;

/**
 * @author Nikolay Moskal
 *
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

}
