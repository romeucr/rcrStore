	package com.rcrdev.rcrstore.services.validation;
	
	import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rcrdev.rcrstore.domain.enums.ClientType;
import com.rcrdev.rcrstore.dto.ClientNewDTO;
import com.rcrdev.rcrstore.resources.exceptions.FieldMessage;
import com.rcrdev.rcrstore.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> { //nome da anotacao e tipo de dado que vai aceitar a anotacao
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>(); //

		// inclua os testes aqui, inserindo erros na lista. Se a lista de erros (List<FieldMessage>) estiver vazia, retorna false (nao há erros. Se há erros, é adicionado Há lista e retorna true

		if (objDto.getType().equals(ClientType.PERSON.getCode()) && !BR.isValidCPF(objDto.getClientIdNumber())) {
			list.add(new FieldMessage("clientIdNumber", "Client Id Number(CPF) Invalid!"));
		}

		if (objDto.getType().equals(ClientType.COMPANY.getCode()) && !BR.isValidCNPJ(objDto.getClientIdNumber())) {
			list.add(new FieldMessage("clientIdNumber", "Client Id Number(CNPJ) Invalid!"));
		}
		
		
		for (FieldMessage e : list) { //como nao trabalha com FieldMessage (criado por nos), este FOR adiciona (para cada erro encontrado acima, um erro correspondente na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
