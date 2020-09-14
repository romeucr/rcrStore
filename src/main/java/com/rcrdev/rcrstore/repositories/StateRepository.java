package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
