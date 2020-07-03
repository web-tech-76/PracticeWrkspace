package jwt.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.app.resources.UserAuthorities;

public interface UserAuthoritiesRepository extends JpaRepository<UserAuthorities, Integer> {

}
