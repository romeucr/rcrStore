package com.rcrdev.rcrstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rcrdev.rcrstore.domain.Address;
import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.domain.City;
import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.domain.Product;
import com.rcrdev.rcrstore.domain.State;
import com.rcrdev.rcrstore.domain.enums.ClientType;
import com.rcrdev.rcrstore.repositories.AddressRepository;
import com.rcrdev.rcrstore.repositories.CategoryRepository;
import com.rcrdev.rcrstore.repositories.CityRepository;
import com.rcrdev.rcrstore.repositories.ClientRepository;
import com.rcrdev.rcrstore.repositories.ProductRepository;
import com.rcrdev.rcrstore.repositories.StateRepository;

@SpringBootApplication
public class RcrStoreApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
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
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "Sao Paulo");
		
		City c1 = new City(null, "Uberlandia", est1);
		City c2 = new City(null, "Sao Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@email.com", "12376524533", ClientType.PERSON);
		cli1.getphoneNumbers().addAll(Arrays.asList("1287652323", "1287653434"));
		
		Address add1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "20570000", cli1, c1);
		Address add2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877625221", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(add1, add2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(add1, add2));
		
	}

}
