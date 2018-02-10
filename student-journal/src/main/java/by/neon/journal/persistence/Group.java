/**
 * Group.java
 */
package by.neon.journal.persistence;

import java.util.Set;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "groups")
public class Group implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private int code;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
	private Speciality speciality;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Student> students;

	/**
	 * Builds a new object of Group
	 */
	public Group() {
	}

	/**
	 * Builds a new object of Group
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name of group
	 * @param code
	 *            the group code
	 */
	public Group(int id, String name, int code) {
		this.setId(id);
		this.setName(name);
		this.setCode(code);
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
	 * @return the speciality
	 */
	public Speciality getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality
	 *            the speciality to set
	 */
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the students
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * @param students
	 *            the students to set
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
