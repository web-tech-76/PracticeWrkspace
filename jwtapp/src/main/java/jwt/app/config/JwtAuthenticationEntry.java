package jwt.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class JwtAuthenticationEntry implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		  
		System.out.println(" I am in exception AuthEntryPoint" + HttpServletResponse.SC_UNAUTHORIZED);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
