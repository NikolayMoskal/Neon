/**
 * UserService.java
 */
package by.neon.journal.service;

import by.neon.journal.persistence.JwtUser;

/**
 * @author Nikolay Moskal
 *
 */
public interface UserService {
	JwtUser findByName(String username);
}
