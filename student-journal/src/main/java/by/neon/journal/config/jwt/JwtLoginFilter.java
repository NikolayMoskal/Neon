/**
 * JWTAuthenticationFilter.java
 */
package by.neon.journal.config.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.neon.journal.persistence.AuthenticationUser;

/**
 * @author Nikolay Moskal
 *
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
	/**
	 * Builds a new object of JWTAuthenticationFilter
	 * 
	 * @param defaultFilterProcessesUrl
	 */
	public JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		AuthenticationUser creds = new ObjectMapper().readValue(request.getInputStream(), AuthenticationUser.class);
		return getAuthenticationManager()
				.authenticate((Authentication) new UsernamePasswordAuthenticationToken(creds.getUsername(),
						creds.getPassword(), Collections.emptyList()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		TokenUtils.addAuthentication(response, ((User)authResult.getPrincipal()).getUsername());
	}
}
