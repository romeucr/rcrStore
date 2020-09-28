package com.rcrdev.rcrstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.repositories.CategoryRepository;
import com.rcrdev.rcrstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

		"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null); //objeto novo tem que ter id nulo, se estiver valendo alguma coisa, o metodo vai considerar uma atualizacao e nao insercao
		return repo.save(obj); //metodo save do Spring Data serve tanto para inserir quanto para atualizar. Quando id foi nulo, insere. Quando nao, atualiza
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
}
