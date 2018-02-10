/**
 * 
 */
package by.neon.journal.service;

import java.util.List;

import by.neon.journal.persistence.University;

/**
 * @author Nikolay Moskal
 *
 */
public interface UniversityService {
    /**
     * @param university
     * @return
     */
    University add(University university);
    /**
     * @param university
     */
    void delete(University university);
    /**
     * @param university
     */
    void update(University university);
    /**
     * @return
     */
    List<University> getAll();
    /**
     * @param name
     * @return
     */
    University findByName(String name);
}
