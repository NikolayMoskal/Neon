/**
 * SecurityConstants.java
 */
package by.neon.journal.config.jwt;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import by.neon.journal.persistence.JwtUser;
import by.neon.journal.service.implementation.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Nikolay Moskal
 *
 */
@Component
public final class TokenUtils {
	private static final String SECRET = "SecretKeyToGenJWTs";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days
	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String HEADER_STRING = "Authorization";
	private static UserDetailsServiceImpl userDetailsService;

	public static void addAuthentication(HttpServletResponse res, String username) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("role", userDetailsService.findByName(username).getRole());
		String JWT = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
			if (claims.getSubject() != null) {
				JwtUser user = new JwtUser(claims.getSubject(), (String) claims.get("role"));
				List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
				return new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);
			}
			return null;
		}
		return null;
	}

	/**
	 * @param userDetailsService the userDetailsService to set
	 */
	@Autowired
	public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
		TokenUtils.userDetailsService = userDetailsService;
	}
}
