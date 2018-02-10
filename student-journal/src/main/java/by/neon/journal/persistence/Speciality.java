/**
 * Speciality.java
 */
package by.neon.journal.persistence;

import java.util.Set;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "speciality")
public class Speciality implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speciality_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private int code;

	@Column(name = "count_years")
	private int countYears;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cathedra_id", referencedColumnName = "cathedra_id")
	private Cathedra cathedra;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
	private Set<Group> groups;

	/**
	 * Builds a new object of Speciality
	 */
	public Speciality() {
	}

	/**
	 * Builds a new object of Speciality
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name of speciality
	 * @param code
	 *            the speciality code
	 * @param countYears
	 *            the count years of learning
	 */
	public Speciality(int id, String name, int code, int countYears) {
		this.setId(id);
		this.setName(name);
		this.setCode(code);
		this.setCountYears(countYears);
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
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the countYears
	 */
	public int getCountYears() {
		return countYears;
	}

	/**
	 * @param countYears
	 *            the countYears to set
	 */
	public void setCountYears(int countYears) {
		this.countYears = countYears;
	}

	/**
	 * @return the cathedra
	 */
	public Cathedra getCathedra() {
		return cathedra;
	}

	/**
	 * @param cathedra
	 *            the cathedra to set
	 */
	public void setCathedra(Cathedra cathedra) {
		this.cathedra = cathedra;
	}

	/**
	 * @return the groups
	 */
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
