package jwt.app.resources;

import java.io.Serializable;

import lombok.Data;



public @Data class JwtRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private String email;
	
	private Long phone;
	
}
