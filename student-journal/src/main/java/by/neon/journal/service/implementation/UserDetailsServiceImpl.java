/**
 * UserDetailsServiceImpl.java
 */
package by.neon.journal.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.neon.journal.persistence.JwtUser;
import by.neon.journal.repository.UserRepository;
import by.neon.journal.service.UserService;

/**
 * @author Nikolay Moskal
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public JwtUser findByName(String username) throws UsernameNotFoundException {
		List<JwtUser> users = userRepository.findByUserName(username);
		if (users == null) {
			throw new UsernameNotFoundException(username);
		}
		List<String> roles = new ArrayList<>();
		for (JwtUser user : users)
			roles.add(user.getRole());
		String rolesList = String.join(",", roles);
		return new JwtUser(username, rolesList);
	}

}
