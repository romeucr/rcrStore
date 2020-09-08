package com.rcrdev.rcrstore.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rcrdev.rcrstore.domain.Category;

@RestController //to inform that the class is a Rest Controller
@RequestMapping(value="/categories") //the endpoint
public class CategoryResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Category> list() {
		
		Category cat1 = new Category(1, "Informatics");
		Category cat2 = new Category(2, "Office");
		
		List<Category> list = new ArrayList<>();
		
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}
}
