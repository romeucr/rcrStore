package com.rcrdev.rcrstore.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	//@Param informa que os campos sao os parametros da consulta JPQL. os nomes devem ser iguais!
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories")List<Category> categories, Pageable pageRequest);

	//a mesma consulta sem utilizar o @Query, basta colocar o nome do método como abaixo e retirar os params do argumento
	//utilizando a query y o nome do metodo ao mesmo tempo, a query tem prioridade sobre o nome do metodo e é executada
	// 	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);

	
}
