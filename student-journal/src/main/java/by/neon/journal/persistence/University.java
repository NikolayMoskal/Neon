/**
 * University.java
 */
package by.neon.journal.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "university")
public class University implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "university_id")
	private int id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
	private Set<Faculty> faculties;

	/**
	 * Builds a new object of University
	 */
	public University() {
		this.faculties = new HashSet<Faculty>(0);
	}

	/**
	 * Builds a new object of University
	 * 
	 * @param id
	 *            the object ID in database
	 * @param name
	 *            the title of university
	 */
	public University(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "University [name=" + name + "]";
	}

	/**
	 * @return the faculties
	 */
	public Set<Faculty> getFaculties() {
		return faculties;
	}

	/**
	 * @param faculties
	 *            the faculties to set
	 */
	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}
}
