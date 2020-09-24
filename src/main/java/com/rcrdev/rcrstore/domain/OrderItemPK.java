package com.rcrdev.rcrstore.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable //quando uma entidade tem como atributo outra classe, colocar para dizer que é um subtipo
public class OrderItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//chave privada pedido/produto. faz a intermediacao dos dois. Nao precisa construtor
	@ManyToOne
	@JoinColumn(name="client_order_id")
	private ClientOrder clientOrder;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	public ClientOrder getClientOrder() {
		return clientOrder;
	}
	public void setClientOrder(ClientOrder clientOrder) {
		this.clientOrder = clientOrder;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//hash code and equals gerado com base nos dois atributos
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientOrder == null) ? 0 : clientOrder.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (clientOrder == null) {
			if (other.clientOrder != null)
				return false;
		} else if (!clientOrder.equals(other.clientOrder))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
}
