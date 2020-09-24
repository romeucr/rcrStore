package com.rcrdev.rcrstore.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	//chave composta que faz a interligacao entre producto e pedido
	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Double descount;
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	//OrderItemPK id foi trocado por pedido e produto no construtor
	public OrderItem(ClientOrder clientOrder, Product product, Double descount, Integer quantity, Double price) {
		super();
		id.setClientOrder(clientOrder);
		id.setProduct(product);
		this.descount = descount;
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public ClientOrder getClientOrder() {
		return id.getClientOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public OrderItemPK getId() {
		return id;
	}
	
	public void setId(OrderItemPK id) {
		this.id = id;
	}
	
	public Double getDescount() {
		return descount;
	}
	
	public void setDescount(Double descount) {
		this.descount = descount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
