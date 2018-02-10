package by.neon.journal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.neon.journal.config.HibernateConfiguration;
import by.neon.journal.persistence.University;
import by.neon.journal.service.UniversityService;

/**
 * @author Nikolay Moskal
 *
 */
public class App {
    /**
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
	ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
	UniversityService sessionFactory = (UniversityService) context.getBean("universityService");
	for (University university : sessionFactory.getAll())
	    System.out.println(university.getName());
    }
}
