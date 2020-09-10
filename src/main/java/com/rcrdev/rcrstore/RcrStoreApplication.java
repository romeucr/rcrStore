package com.rcrdev.rcrstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.repositories.CategoryRepository;

@SpringBootApplication
public class RcrStoreApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RcrStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatics");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
