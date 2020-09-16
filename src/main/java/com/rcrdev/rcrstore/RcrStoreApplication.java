package com.rcrdev.rcrstore;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rcrdev.rcrstore.domain.Address;
import com.rcrdev.rcrstore.domain.CardPayment;
import com.rcrdev.rcrstore.domain.Category;
import com.rcrdev.rcrstore.domain.City;
import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.domain.ClientOrder;
import com.rcrdev.rcrstore.domain.Payment;
import com.rcrdev.rcrstore.domain.Product;
import com.rcrdev.rcrstore.domain.State;
import com.rcrdev.rcrstore.domain.TicketPayment;
import com.rcrdev.rcrstore.domain.enums.ClientType;
import com.rcrdev.rcrstore.domain.enums.PaymentStatus;
import com.rcrdev.rcrstore.repositories.AddressRepository;
import com.rcrdev.rcrstore.repositories.CategoryRepository;
import com.rcrdev.rcrstore.repositories.CityRepository;
import com.rcrdev.rcrstore.repositories.ClientOrderRepository;
import com.rcrdev.rcrstore.repositories.ClientRepository;
import com.rcrdev.rcrstore.repositories.PaymentRepository;
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
	
	@Autowired
	private ClientOrderRepository clientOrderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
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
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "Sao Paulo");
		
		City c1 = new City(null, "Uberlandia", state1);
		City c2 = new City(null, "Sao Paulo", state2);
		City c3 = new City(null, "Campinas", state2);
		
		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@email.com", "12376524533", ClientType.PERSON);
		cli1.getphoneNumbers().addAll(Arrays.asList("1287652323", "1287653434"));
		
		Address add1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "20570000", cli1, c1);
		Address add2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877625221", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(add1, add2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(add1, add2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		ClientOrder cliOrd1 = new ClientOrder(null, sdf.parse("30/09/2017 10:32"), cli1, add1);
		ClientOrder cliOrd2 = new ClientOrder(null, sdf.parse("10/10/2017 19:35"), cli1, add2);
		
		Payment pay1 = new CardPayment(null, PaymentStatus.PAID, cliOrd1, 6);
		cliOrd1.setPayment(pay1);
		
		Payment pay2 = new TicketPayment(null, PaymentStatus.PENDING, cliOrd2, sdf.parse("20/10/2017 00:00"), null);
		cliOrd2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(cliOrd1, cliOrd2));
		
		clientOrderRepository.saveAll(Arrays.asList(cliOrd1, cliOrd2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
	}

}
