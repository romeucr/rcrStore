package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
