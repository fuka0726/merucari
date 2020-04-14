package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domein.Category;
import com.example.repository.CategoryRepository;

public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAllCategories() {
		List<Category> daiCategories =repository.findByParentId(null);
		for (Category daiCategory : daiCategories) {
			List<Category> chuCategories = repository.findByParentId(daiCategory.getId());
			
			daiCategory.setChildCategories(chuCategories);
			
			for (Category chuCategory : chuCategories) {
				List<Category> syoCategories = repository.findByParentId(chuCategory.getId());
				chuCategory.setChildCategories(syoCategories);
			}
			
		}
		return daiCategories;
	}
	
	
}
