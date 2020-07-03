package jwt.app.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jwt.app.services.UserAuthService;
import jwt.app.util.TokenManager;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	
	@Autowired
	UserAuthService userJwtAuthService;
	
	
	@Autowired
	TokenManager tokenManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println(" *************** new Request ***************************");
		
		String username = null;
		UsernamePasswordAuthenticationToken  authentication=null;
		UserDetails userDetails =null;
		
		String token = getTokenFromRequest(request);
		
		System.out.println("Token is " + token);
		if(null!= token) 
		{
			
			username = tokenManager.getUserFromToken(token);
			
			System.out.println("in Filter user.... " + username);
			
			//Authentication userAuthentication= SecurityContextHolder.getContext().getAuthentication();
			
			//System.out.println("name " + userAuthentication.getName());
			//System.out.println("credentials " + userAuthentication.getCredentials().toString());
			
			//userAuthentication.getAuthorities().stream().forEach(System.out::println);
			
			
			if(null!= username && SecurityContextHolder.getContext().getAuthentication()==null)
				{
			
					userDetails =this.userJwtAuthService.loadUserByUsername(username);
					
					System.out.println(" Filter user Details :  "  + userDetails.getUsername() );
					
						
					 userDetails.getAuthorities().stream().forEach(System.out::println);
					
				if(tokenManager.validateToken(token, userDetails)) {
				
					authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}
			}
			
		}
		else {
				System.out.println(" no token received");
		}
		
		filterChain.doFilter(request, response);	
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		
		String token= request.getHeader("Authorization");
		
		 
		System.out.println("token:   " + token);
		
		 if((null!=token && StringUtils.hasText(token)) )
		  { 
			  token=token.substring(7, token.length());
			  System.out.println("token after removing prefix is ... " + token );
			  return  token;
		  }
		  else {
		
			  return null;
		  }
		
	}
	
	
	
}
