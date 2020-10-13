package com.rcrdev.rcrstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rcrdev.rcrstore.domain.enums.PaymentStatus;

/*mapping the inheritance using different tables for 
TicketPayment and CardPayment. Other option is use a single table with all the fields 
of both classes.*/
@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
public abstract class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	 /*Its the same in Order, so was used the annotations @JoinColumn and @MapsId do indicates that. 1to1 mapping */
	@Id
	private Integer id;
	private Integer status; 
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId 
	private ClientOrder order;
	
	public Payment() {
	}

	public Payment(Integer id, PaymentStatus status, ClientOrder order) {
		super();
		this.id = id;
		this.status = (status==null) ? null : status.getCode();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCode();
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
