package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	/*When using superclasses, theres no need to create the repository for the child classes
	 * (TicketPayment and CardPayment in this case)*/

}
