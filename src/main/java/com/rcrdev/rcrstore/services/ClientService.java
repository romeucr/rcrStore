package com.rcrdev.rcrstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.repositories.ClientRepository;
import com.rcrdev.rcrstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

		"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}	
	
}
