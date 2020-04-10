package com.example.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domein.Item;
import com.example.form.AddForm;
import com.example.service.AddItemService;

@Controller
@RequestMapping("")
public class AddItemController {

	@Autowired
	private AddItemService addItemService;
	
	
	/**
	 * フォームオブジェクトをリクエストスコープに格納.
	 * @return フォーム
	 */
	@ModelAttribute
	public AddForm setUpAddForm() {
		return new AddForm();
	}
	
	/**
	 * 商品追加画面
	 * @return
	 */
	@RequestMapping("/add-item")
	public String showAddItem() {
		return "add";
	}
	
	@RequestMapping("/add")
	public String insert(AddForm form) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		addItemService.insert(item);
		return "redirect:/add-item";
	}
	

	
}
