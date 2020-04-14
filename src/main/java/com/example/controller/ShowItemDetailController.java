package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domein.Item;
import com.example.service.ShowItemDetailService;
import com.example.service.ShowItemListService;


/**
 * 商品詳細情報を操作するコントローラー.
 * @author fuka
 *
 */
@Controller
@RequestMapping("")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService ShowItemDetailService;
	
	@Autowired
	private ShowItemListService ShowItemListService;
	
	
	/**
	 * 商品の詳細情報を表示します.
	 * @param trainId　リクエストパラメータ(商品id)
	 * @param model リクエストスコープ
	 * @return　商品詳細情報
	 */
	@RequestMapping("/showItemDetail")
	public String showItemDetail(String id, Model model) {
		Item item = ShowItemDetailService.showItemDetail(Integer.parseInt(id));
		model.addAttribute("item", item);
		return "detail";
	}
	
	
	
	
}
