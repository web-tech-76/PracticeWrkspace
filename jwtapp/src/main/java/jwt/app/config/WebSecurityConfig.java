package jwt.app.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jwt.app.filters.JwtAuthFilter;

@Configuration
@EnableWebSecurity //(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	private JwtAuthenticationEntry authExceptionHandler;
	
	
	@Autowired
	@Resource(name = "userAuthService")
	private UserDetailsService userDetailsService;
	
	
	@Bean 
	public JwtAuthFilter JwtAuthFilter() {
		return new JwtAuthFilter();
	}
	
	
		
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	@Override
    @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{

		try {
				http.cors();
				http
					.csrf()
					.disable()
					.exceptionHandling()
					.authenticationEntryPoint(authExceptionHandler)
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers("/", "/*.js")
					.permitAll()
					.antMatchers("/auth/**")
					.permitAll()
					.anyRequest()
					.authenticated();
					
					http.addFilterBefore(JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	
		} catch (Exception e) {
			System.out.println("exception in security Config" + e);
		}
	}
}
