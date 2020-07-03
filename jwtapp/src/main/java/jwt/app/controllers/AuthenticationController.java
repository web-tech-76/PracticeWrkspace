package jwt.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.app.resources.ApiResponse;
import jwt.app.resources.AuthUser;
import jwt.app.resources.Authorities;
import jwt.app.resources.CustomUserDetails;
import jwt.app.resources.JwtRequest;
import jwt.app.services.UserAuthService;
import jwt.app.util.TokenManager;

@CrossOrigin
@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	TokenManager tokenManager;

	@Autowired
	UserAuthService userAuthService;

	
		
	@PostMapping("token")
	public ApiResponse<AuthUser> loggedin(@RequestBody JwtRequest jwtUser) {

		String token = null;

		CustomUserDetails userDetails= null;
		List<String> roles=  new ArrayList<String>();
		
		try {

				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
						jwtUser.getUsername()
						,jwtUser.getPassword()
						
						));
				
				
				
				System.out.println(" I am through");
							
				userDetails = (CustomUserDetails) userAuthService.loadUserByUsername(jwtUser.getUsername());
				
				jwtUser.setEmail(userDetails .getEmail());
				jwtUser.setPhone(userDetails .getPhone());
				
				
				for (Authorities authorities : userDetails.getAuthRoles()) {
					roles.add(authorities.getAuthority());
				}
				
				
				System.out.println("Controller ******************");
				
				System.out.println(userDetails.getUsername());
				token = tokenManager.createClaimJwtToken(userDetails);
				
				System.out.println("token:    " + token + "\n");
				
				return new ApiResponse<AuthUser>(HttpStatus.OK.value(), "logging success", jwtUser, token);
			} 
			
			catch (Exception e) {
				
				System.out.println("exception controller : " + e.getMessage());
				return new ApiResponse<AuthUser>(HttpStatus.BAD_REQUEST.value(), "something went wrong", jwtUser, null);
			}

		
	}

}
