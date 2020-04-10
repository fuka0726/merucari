package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domein.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * @author fuka
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * 商品情報を取得します.
	 * @param train_id
	 * @return 商品情報
	 */
	public Item showItemDetail(Integer id) {
		Item item = itemRepository.load(id);
		return item;
	}
	 
	
	
	
	
	
}
