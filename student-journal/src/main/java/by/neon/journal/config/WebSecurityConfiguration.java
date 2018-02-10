/**
 * WebSecurityConfiguration.java
 */
package by.neon.journal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import by.neon.journal.config.jwt.JwtAuthenticationEntryPoint;
import by.neon.journal.config.jwt.JwtAuthenticationFilter;
import by.neon.journal.config.jwt.JwtLoginFilter;

/**
 * @author Nikolay Moskal
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM type_user WHERE username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
				.formLogin()
				  	.loginPage("/login")
				  	.failureUrl("/login?error")
				  	.defaultSuccessUrl("/")
				  	.usernameParameter("username")
				  	.passwordParameter("password")
				  	.permitAll()
				  	.and()
				.logout()
					.logoutSuccessUrl("/login?logout")
					.permitAll()
					.and()
				.exceptionHandling()
				  	.accessDeniedPage("/403")
				  	.authenticationEntryPoint(entryPoint)
				  	.and()
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/protected/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtAuthenticationFilter(), 
						UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
