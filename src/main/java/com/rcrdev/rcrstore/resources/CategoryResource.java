package com.rcrdev.rcrstore.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //to inform that the class is a Rest Controller
@RequestMapping(value="/categories") //the endpoint
public class CategoryResource {

	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "Funciona!";
	}
}
