/**
 * 
 */
package by.neon.journal.service.implementation;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import by.neon.journal.persistence.University;
import by.neon.journal.repository.UniversityRepository;
import by.neon.journal.service.UniversityService;

/**
 * @author Nikolay Moskal
 *
 */
@Service("universityService")
public class UniversityServiceImpl implements UniversityService {
	private final Logger logger = LoggerFactory.getLogger(UniversityServiceImpl.class);
	@Autowired
	private UniversityRepository universityRepository;
	@Autowired
	private MessageSource messageSource;

	// @Autowired
	// private EntityManager entityManager;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.neon.journal.service.UniversityService#add(by.neon.journal.persistence
	 * .University)
	 */
	public University add(University university) {
		logger.info("The university " + university.getName() + " was saved");
		return universityRepository.saveAndFlush(university);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.neon.journal.service.UniversityService#delete(by.neon.journal.
	 * persistence.University)
	 */
	public void delete(University university) {
		logger.info("University " + university.getName() + " was deleted");
		universityRepository.delete(university);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.neon.journal.service.UniversityService#update(by.neon.journal.
	 * persistence.University)
	 */
	public void update(University university) {
		logger.info("The university " + university.getName() + " was updated");
		universityRepository.saveAndFlush(university);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.neon.journal.service.UniversityService#getAll()
	 */
	public List<University> getAll() {
		// return (List<University>)entityManager.createQuery("SELECT c FROM University c").getResultList();
		//logger.info("Output of all universities");
		logger.info(messageSource.getMessage("service.out", new Object[]{"universities"}, new Locale("ru")));
		return universityRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.neon.journal.service.UniversityService#findByName(java.lang.String)
	 */
	public University findByName(String name) {
		return universityRepository.findByName(name);
	}

}
