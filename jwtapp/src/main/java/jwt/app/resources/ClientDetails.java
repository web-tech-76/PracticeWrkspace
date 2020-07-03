package jwt.app.resources;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "client_details")
public @Data class ClientDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String clientId;
	
	private String secret;
	
	private String resouceIds;
	private String scope;
	
	private String authGrantTypes;
	private String redirectUrl;
	
	private String authorities;
	
	private int tokenValidity;
	
	private int refreshValidity;
	
	private String additionalInfo;
	
	private String autoApprove;
	
}
