package jwt.app.resources;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Entity
@Table(name = "authuser")
public @Data class AuthUser implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	public AuthUser() {}
	
	
	public AuthUser(AuthUser authuser) {
		BeanUtils.copyProperties(authuser, this);
	}
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	
	
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	
	
	@JoinTable( name = "userauthorities", 
			joinColumns = @JoinColumn(name ="user_id",referencedColumnName = "id")
			,
			inverseJoinColumns = @JoinColumn (name = "authority", referencedColumnName = "id")
	)
	@OneToMany(fetch = FetchType.EAGER)
	Collection<Authorities> authRoles;

}
