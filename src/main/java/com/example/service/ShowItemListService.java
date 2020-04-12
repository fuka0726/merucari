package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domein.Item;
import com.example.form.SearchForm;
import com.example.repository.ItemRepository;


/**
 * 商品一覧を表示するサービスクラス.
 * @author fuka
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * 商品情報を全件取得します
	 * @return
	 */
	public List<Item> showItemList(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	public List<Item> search(SearchForm searchForm) {
		return itemRepository.search(searchForm);
	}
	
	public Integer searchCount(SearchForm searchForm) {
		return itemRepository.searchCount(searchForm);
	}
	
}
