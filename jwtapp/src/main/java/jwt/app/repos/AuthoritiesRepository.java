package jwt.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.app.resources.Authorities;

public interface AuthoritiesRepository extends  JpaRepository<Authorities, Integer> {

}
