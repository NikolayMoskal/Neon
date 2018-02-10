/**
 * Student.java
 */
package by.neon.journal.persistence;

import javax.persistence.*;

/**
 * @author Nikolay Moskal
 *
 */
@Entity
@Table(name = "student")
public class Student implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "group_id")
	private Group group;

	/**
	 * Builds a new object of Student
	 */
	public Student() {
	}

	/**
	 * Builds a new object of Student
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the student name
	 */
	public Student(int id, String name) {
		this.setId(id);
		this.setName(name);
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
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
}
