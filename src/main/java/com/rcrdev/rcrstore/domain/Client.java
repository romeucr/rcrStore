package com.rcrdev.rcrstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rcrdev.rcrstore.domain.enums.ClientType;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique=true) //nao deixa inserir dois emails iguais no BD
	private String email;
	private String clientIdNumber;
	private Integer type; //implementation to manage enum by id
	
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL) //toda operacao que afete cliente irá afetar também os enderecos. Se apaga cliente, apaga tbm os enderecos
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String> phoneNumbers = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<ClientOrder> orders = new ArrayList<>();
	
	public Client() {
	}

	public Client(Integer id, String name, String email, String clientIdNumber, ClientType type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.clientIdNumber = clientIdNumber;
		this.type = (type==null) ? null : type.getCode(); //implementation to manage enum by id. Verificando que se for nulo, meter nulo mesmo. Sem isso causa um nullpointer exception ao tentar dar um PUT
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

	public String getClientIdNumber() {
		return clientIdNumber;
	}

	public void setClientIdNumber(String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}

	//implementation to manage enum by id
	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	//implementation to manage enum by id
	public void setType(ClientType type) {
		this.type = type.getCode();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getphoneNumbers() {
		return phoneNumbers;
	}

	public void setphoneNumber(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	public List<ClientOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ClientOrder> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
