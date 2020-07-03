package jwt.app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.app.resources.AuthUser;

@Repository
public interface LoginUserRepository extends JpaRepository<AuthUser ,Integer>  {
	
	public Optional<AuthUser> findByUsername(String username);
	
	public Optional<AuthUser> findByPhone(String phone);
	
	public Optional<AuthUser> findByEmail(String email);
	
}
