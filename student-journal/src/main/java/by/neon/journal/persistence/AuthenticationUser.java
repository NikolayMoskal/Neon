/**
 * AuthenticationUser.java
 */
package by.neon.journal.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name="users")
public class AuthenticationUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	/**
	 * Builds a new object of AuthenticationUser
	 * 
	 */
	public AuthenticationUser() {
	}
	
	/**
	 * Builds a new object of AuthenticationUser
	 * 
	 * @param username
	 * @param password
	 */
	public AuthenticationUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
