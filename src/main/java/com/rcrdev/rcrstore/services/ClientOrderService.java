package com.rcrdev.rcrstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcrdev.rcrstore.domain.ClientOrder;
import com.rcrdev.rcrstore.repositories.ClientOrderRepository;
import com.rcrdev.rcrstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientOrderService {

	@Autowired
	private ClientOrderRepository repo;

	public ClientOrder find(Integer id) {
		Optional<ClientOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

		"Object not found! Id: " + id + ", Type: " + ClientOrder.class.getName()));
	}	
	
}
