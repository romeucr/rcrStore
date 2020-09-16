package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.ClientOrder;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {

}
