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
@Table(name="authorities")
public  @Data class Authorities implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Authorities() {}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "authority")
	private String authority;
	
	@Column(name = "privileges")
	private String privileges;
	
	@Column(name = "precedence")
	private int precedence;
	
	@Column(name = "description")
	private String description;
	
}
