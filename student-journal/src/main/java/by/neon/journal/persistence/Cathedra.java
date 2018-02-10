/**
 * Cathedra.java
 */
package by.neon.journal.persistence;

import java.util.Set;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "cathedra")
public class Cathedra implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cathedra_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "count_workers")
	private int countWorkers;

	@Column(name = "boss_name")
	private String bossName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
	private Faculty faculty;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cathedra")
	private Set<Speciality> specialities;

	/**
	 * Builds a new object of Cathedra
	 * 
	 */
	public Cathedra() {
	}

	/**
	 * Builds a new object of Cathedra
	 * 
	 * @param name
	 *            the name of cathedra
	 * @param countWorkers
	 *            the count of workers in this cathedra
	 * @param bossName
	 *            the boss name
	 */
	public Cathedra(String name, int countWorkers, String bossName) {
		this.setName(name);
		this.setCountWorkers(countWorkers);
		this.setBossName(bossName);
	}

	/**
	 * @return the cathedraId
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the cathedraId to set
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
	 * @return the countWorkers
	 */
	public int getCountWorkers() {
		return countWorkers;
	}

	/**
	 * @param countWorkers
	 *            the countWorkers to set
	 */
	public void setCountWorkers(int countWorkers) {
		this.countWorkers = countWorkers;
	}

	/**
	 * @return the bossName
	 */
	public String getBossName() {
		return bossName;
	}

	/**
	 * @param bossName
	 *            the bossName to set
	 */
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cathedra [name=" + name + ", countWorkers=" + countWorkers + ", bossName=" + bossName + "]";
	}

	/**
	 * @return the university
	 */
	public Faculty getUniversity() {
		return faculty;
	}

	/**
	 * @param faculty
	 *            the faculty to set
	 */
	public void setUniversity(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * @return the specialities
	 */
	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	/**
	 * @param specialities
	 *            the specialities to set
	 */
	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}
}
