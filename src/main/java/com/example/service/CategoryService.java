package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domein.Category;
import com.example.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
	
	/**
	 * 大カテゴリーから中、小カテゴリー情報をとりだす
	 * @return
	 */
	public List<Category> findAllCategories() {
		//親idがnullの大カテゴリーのリスト
		List<Category> daiCategories =repository.findByParentId(null);
	
		for (Category daiCategory : daiCategories) {
			//大カテゴリーからidを取得した中カテゴリーのリスト
			List<Category> chuCategories = repository.findByParentId(daiCategory.getId());
			//大カテゴリーの子カテゴリーとして中カテゴリーをセット
			daiCategory.setChildCategories(chuCategories);
			
			for (Category chuCategory : chuCategories) {
				//中カテゴリーからidを取得した小カテゴリーリスト
				List<Category> syoCategories = repository.findByParentId(chuCategory.getId());
				//中カテゴリーの子カテゴリーとして小カテゴリーをセット
				chuCategory.setChildCategories(syoCategories);
			}
			
		}
		return daiCategories;
	}
	
	
}
