package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domein.Item;
import com.example.repository.ItemRepository;

/**
 * 商品登録の業務処理を行うサービスクラスです.
 * @author fuka
 *
 */
@Service
@Transactional
public class AddItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * repositoryのinsertメソッドの呼び出し.
	 * 
	 * @param original 商品情報
	 */
	public void insert(Item item) {
		itemRepository.insert(item);
	}
	
	
	
}
