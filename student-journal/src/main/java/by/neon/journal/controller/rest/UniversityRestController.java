/**
 * UniversityRestController.java
 */
package by.neon.journal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.neon.journal.persistence.University;
import by.neon.journal.service.UniversityService;

/**
 * @author Nikolay Moskal
 *
 */
@RestController
public class UniversityRestController {
	@Autowired
	private UniversityService universityService;

	/**
	 * Returns the object of {@link University} from database
	 * 
	 * @param name
	 *            the name of university
	 * @return University
	 */
	@RequestMapping(value = { "/rest/university" }, method = RequestMethod.GET)
	public ResponseEntity<University> getUniversity(@RequestParam(value="name", defaultValue="GRSU", required=false) String name) {
		University university = universityService.findByName(name);
		if (university == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<University>(university, HttpStatus.OK);
	}
}
