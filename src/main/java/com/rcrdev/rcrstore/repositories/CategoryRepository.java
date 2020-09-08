package com.rcrdev.rcrstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcrdev.rcrstore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
