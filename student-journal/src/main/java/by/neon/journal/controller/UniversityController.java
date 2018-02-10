/**
 * UniversityController.java
 */
package by.neon.journal.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.neon.journal.persistence.University;
import by.neon.journal.service.UniversityService;

/**
 * @author Nikolay Moskal
 *
 */
@Controller
public class UniversityController {
	private final Logger logger = LoggerFactory.getLogger(UniversityController.class);
	@Autowired
	private UniversityService universityService;

	/**
	 * Shows the page Hello.jsp with current locale
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/", "/hello**" }, method = RequestMethod.GET)
	public ModelAndView helloPage() {
		ModelAndView model = new ModelAndView();
		Locale locale = LocaleContextHolder.getLocale();
		model.addObject("message", "This is welcome page");
		model.addObject("locale", locale);
		model.setViewName("hello");
		logger.info("The page Hello.jsp was opened");
		return model;
	}

	/**
	 * Shows the page Protected.jsp with current locale
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/protected**" }, method = RequestMethod.GET)
	public ModelAndView getUniversityInfo() {
		ModelAndView model = new ModelAndView();
		List<University> list = universityService.getAll();
		model.addObject("title", "This is a protected page");
		model.addObject("message", toString(list));
		model.setViewName("protected");
		logger.warn("The page Protected.jsp was opened");
		return model;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			//UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = (String) auth.getPrincipal();
			model.addObject("username", username);
		}
		model.setViewName("error");
		return model;

	}

	private String toString(List<University> list) {
		String result = "<br/>";
		for (University university : list) {
			result += university.toString() + "<br/>";
		}
		return result;
	}
}
