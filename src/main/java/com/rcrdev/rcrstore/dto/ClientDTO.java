package com.rcrdev.rcrstore.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.services.validation.ClientUpdate;

@ClientUpdate //anotacao customizada
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message="Obligatory field.")
	@Length(min=5, max=120, message="field size between 5 and 120 characteres.")
	private String name;
	
	@NotEmpty(message="Obligatory field.")
	@Email(message="invalid email")
	private String email;
	
	public ClientDTO() {
	}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
