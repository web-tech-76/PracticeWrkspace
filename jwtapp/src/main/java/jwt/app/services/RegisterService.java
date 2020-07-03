package jwt.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwt.app.repos.LoginUserRepository;
import jwt.app.resources.AuthUser;

@Service
public class RegisterService {

	@Autowired
	LoginUserRepository loginRepository;
	
		
	public Boolean save(AuthUser authUser) {
		try {
			
			loginRepository.save(authUser);
			return true;
		}
		
		catch(Exception e) {
			return false;
		}
		
	}
	
	
	
}
