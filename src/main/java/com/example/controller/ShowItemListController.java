package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domein.Item;
import com.example.service.ShowItemListService;

@Controller
@RequestMapping("")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@RequestMapping("/")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.showItemList();
		model.addAttribute("itemList", itemList);
		return "list";
	}
	
	
	
	/**
	 * ページング機能を実装します.
	 * 
	 * @param form 並び順を選択するフォーム
	 * @param page ページ数
	 * @param searchName 検索文字列
	 * @param model　モデル
	 * @return 商品一覧画面の○ページ目
	 */
//	@RequestMapping("/to_other_page")
//	public String toOtherPage(Integer page,Model model) {
//		List<Original> originalList = showItemListService.showOriginalList();
//		Integer pageNumber = originalList.size() / 20;
//		List<Integer> pageNumberList = new ArrayList<>();
//		for (int i = 1; i <= pageNumber; i++) {
//			if (i == page) {
//				continue;
//			}
//			pageNumberList.add(i);
//		}
//		model.addAttribute("pageNumberList", pageNumberList);
//		
//		
//		return "list";
//	
//	}
}
