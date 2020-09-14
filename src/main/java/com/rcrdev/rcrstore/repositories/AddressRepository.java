package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
