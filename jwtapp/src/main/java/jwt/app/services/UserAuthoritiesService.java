package jwt.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jwt.app.repos.UserAuthoritiesRepository;
import jwt.app.resources.UserAuthorities;

public class UserAuthoritiesService {

	@Autowired
	UserAuthoritiesRepository userAuthRepository;
	
	
		
	public List<UserAuthorities> getAllUserAuthorities() {
		
		return userAuthRepository.findAll();
	}
	
	public Optional<UserAuthorities> getUserAuthory(int id) {
		
		return userAuthRepository.findById(id);
		
	}
	
	
}
