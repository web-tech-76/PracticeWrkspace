package jwt.app.resources;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="userauthorities")
public @Data class UserAuthorities implements Serializable {

	/**
	 * 
	 */
	
	public UserAuthorities() {}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
		
	@Column(name = "user_id")
	private int user;
	
	@Column(name = "authority")
	private int authority;

}
