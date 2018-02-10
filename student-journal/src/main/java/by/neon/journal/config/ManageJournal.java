/**
 * 
 */
package by.neon.journal.config;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import by.neon.journal.persistence.IEntity;

/**
 * @author Nikolay Moskal
 *
 */
public class ManageJournal {
	private SessionFactory factory;

	/**
	 * Creates a new object of ManageJournal
	 * 
	 * @param factory
	 */
	public ManageJournal(SessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * Shows the list of all objects from one database table
	 * 
	 * @param entity
	 *            a class that mapped to database table
	 */
	public void list(IEntity entity) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List list = session.createQuery("FROM " + entity.getClass().getName()).list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = list.iterator(); iterator.hasNext();) {
				IEntity object = (IEntity) iterator.next();
				System.out.println(object.toString());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Inserts a new object in database
	 * 
	 * @param object
	 *            added object
	 * @return a generated identifier for next object
	 */
	public Integer add(IEntity object) {
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer integer = null;
		try {
			transaction = session.beginTransaction();
			integer = (Integer) session.save(object);
			transaction.commit();
		} catch (HibernateException exception) {
			if (transaction != null)
				transaction.rollback();
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return integer;
	}

	/**
	 * Updates an object by its identifier
	 * 
	 * @param id
	 *            identifier of this object
	 * @param replacement
	 *            a new object
	 */
	public void update(Integer id, IEntity replacement) {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			IEntity object = replacement.getClass().newInstance();
			object = session.get(replacement.getClass(), id);
			for (Field field : replacement.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getName() != "id" && !isDefaultValue(replacement, field))
					field.set(object, field.get(replacement));
			}
			session.update(object);
			transaction.commit();
		} catch (HibernateException exception) {
			if (transaction != null)
				transaction.rollback();
			exception.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Checks the field of represented object for default value
	 * 
	 * @param object
	 *            a persistent object
	 * @param field
	 *            the field of this object
	 * @return <b>true</b> if the field has a default value and <b>false</b> if
	 *         otherwise
	 * @throws ReflectiveOperationException
	 */
	private boolean isDefaultValue(Object object, Field field) throws ReflectiveOperationException {
		field.setAccessible(true);
		try {
			Object value = (field.get(object));
			if (Double.valueOf(value.toString()) == 0.0) // проверка при
				// приведении типа к
				// double
				return true;
		} catch (Exception ex) {
			try {
				return !field.getBoolean(object); // если не получилось
				// привести, пробуем прочитать
				// как boolean
			} catch (Throwable e) {
				return field.get(object) == null; // а если и это не удалось,
				// значит это не примитивный
				// тип
			}
		}
		return false;
	}

	/**
	 * Removes an object by its identifier
	 * 
	 * @param id
	 *            identifier of this object
	 * @param entity
	 *            a class that mapped to database table
	 * @return an old object from database
	 */
	public IEntity delete(Integer id, IEntity entity) {
		Session session = factory.openSession();
		Transaction transaction = null;
		IEntity old = null;
		try {
			transaction = session.beginTransaction();
			old = session.get(entity.getClass(), id);
			session.delete(old);
			transaction.commit();
		} catch (HibernateException exception) {
			if (transaction != null)
				transaction.rollback();
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return old;
	}
}
