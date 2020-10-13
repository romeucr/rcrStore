package com.rcrdev.rcrstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rcrdev.rcrstore.domain.Address;
import com.rcrdev.rcrstore.domain.City;
import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.domain.enums.ClientType;
import com.rcrdev.rcrstore.dto.ClientDTO;
import com.rcrdev.rcrstore.dto.ClientNewDTO;
import com.rcrdev.rcrstore.repositories.AddressRepository;
import com.rcrdev.rcrstore.repositories.ClientRepository;
import com.rcrdev.rcrstore.services.exceptions.DataIntegrityException;
import com.rcrdev.rcrstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(

		"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}	

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj); //com isso, ao atualizar somente um campo, o restante nao será informado como null
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Delete denied. There related entities.");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
	}

	// Page e pagerequest sao objetos do Spring Data para paginaçao
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// em PageRequest, direction é um valor inteiro. feita a conversao de String a
		// inteiro com o Direction.valueOf()
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//metodo auxiliar que transforma ClientDTO em Client
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public Client insert(Client obj) {
		obj.setId(null); 
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return repo.save(obj);
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), 
				objDTO.getClientIdNumber(), ClientType.toEnum(objDTO.getType()));
		
		City cit = new City(objDTO.getCityId(), null, null);
		
		Address add = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getComplement(), 
				objDTO.getNeighbourhood(), objDTO.getZipCode(), cli, cit);
		
		
		cli.getAddresses().add(add);
		
		cli.getphoneNumbers().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			cli.getphoneNumbers().add(objDTO.getPhone2());
		}
		
		if (objDTO.getPhone3() != null) {
			cli.getphoneNumbers().add(objDTO.getPhone3());
		}
		
		return cli;
	}
	
	
}
