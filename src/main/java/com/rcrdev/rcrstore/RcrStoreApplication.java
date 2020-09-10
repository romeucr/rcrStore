package com.rcrdev.rcrstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.domain.Product;
import com.rcrdev.rcrstore.repositories.CategoryRepository;
import com.rcrdev.rcrstore.repositories.ProductRepository;

@SpringBootApplication
public class RcrStoreApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RcrStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatics");
		Category cat2 = new Category(null, "Office");		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));		
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		
	}

}
