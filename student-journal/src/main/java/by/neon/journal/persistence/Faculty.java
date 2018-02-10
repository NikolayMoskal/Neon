/**
 * Faculty.java
 */
package by.neon.journal.persistence;

import java.util.Set;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "faculties")
public class Faculty implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id", referencedColumnName = "university_id")
	private University university;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
	private Set<Cathedra> cathedras;

	/**
	 * Builds a new object of Faculty
	 */
	public Faculty() {
	}

	/**
	 * Builds a new object of Faculty
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the faculty name
	 */
	public Faculty(int id, String name) {
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

	/**
	 * @return the university
	 */
	public University getUniversity() {
		return university;
	}

	/**
	 * @param university
	 *            the university to set
	 */
	public void setUniversity(University university) {
		this.university = university;
	}

	/**
	 * @return the cathedras
	 */
	public Set<Cathedra> getCathedras() {
		return cathedras;
	}

	/**
	 * @param cathedras
	 *            the cathedras to set
	 */
	public void setCathedras(Set<Cathedra> cathedras) {
		this.cathedras = cathedras;
	}
}
