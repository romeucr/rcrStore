	package com.rcrdev.rcrstore.services.validation;
	
	import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.rcrdev.rcrstore.domain.Client;
import com.rcrdev.rcrstore.dto.ClientDTO;
import com.rcrdev.rcrstore.repositories.ClientRepository;
import com.rcrdev.rcrstore.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> { //nome da anotacao e tipo de dado que vai aceitar a anotacao
	
	
	//o HttpServletRequest tem uma funcao que posibilita recuperar o clientId da URI, 
	//uma vez que essa nao é passada no body quando feita um update.
	@Autowired
	private HttpServletRequest request; 
	
	@Autowired
	ClientRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		//inclua os testes aqui, inserindo erros na lista. 
		//Se a lista de erros (List<FieldMessage>) estiver vazia, retorna false (nao há erros. 
		//Se há erros, é adicionado Há lista e retorna true
		
		//estrutura de dados. quando se faz uma requisicao, ela pode ter varios atributos.
		//estes atributos sao armazenados em um map. chaveXvalor
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //pegando os atributos de variaveis da requisicao
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>(); //
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email address already registered!"));
		}
		
		for (FieldMessage e : list) { //como nao trabalha com FieldMessage (criado por nos), este FOR adiciona (para cada erro encontrado acima, um erro correspondente na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
