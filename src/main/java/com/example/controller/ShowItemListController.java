package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domein.Category;
import com.example.form.SearchForm;
import com.example.service.CategoryService;
import com.example.service.ShowItemListService;

@Controller
@RequestMapping("")
public class ShowItemListController {
	
	public static final int ROW_PAR_PAGE = 30;
	
//	private CategoryService categoryService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@ModelAttribute
	private SearchForm setupForm() {
		return new SearchForm();
	}
	
//	@RequestMapping("/")
//	public String showItemList(Model model) {
//		List<Item> itemList = showItemListService.showItemList();
//		model.addAttribute("itemList", itemList);
//		return "list";
//	}
	
	/**
	 * ページング機能
	 * @param searchForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String search(SearchForm searchForm, Model model) {
		//検索フォームが空なら1ページを表示
		if (searchForm.getPage() == null) {
			searchForm.setPage(1);
	}
		// 検索したページの数字
	Integer searchCount = showItemListService.searchCount(searchForm);
		//　最大ページは検索したページの数字/30
	int maxPage = searchCount / ROW_PAR_PAGE;
		//　もしそれが0で割り切れなかったら最大ページを1増やす
	if (searchCount % ROW_PAR_PAGE != 0) {
		maxPage++;
	}
		//検索フォームが最大ページより大きかったら1ページを表示
	if (searchForm.getPage() > maxPage) {
		searchForm.setPage(1);
	}
	//それぞれバリューに名前をつける
	model.addAttribute("itemList", showItemListService.search(searchForm));
	model.addAttribute("maxPage", maxPage);
	model.addAttribute("nowPage", searchForm.getPage()); //最初のページでprevボタンを非表示にさせるため名前をつける
	model.addAttribute("lastPage", searchForm.getPage()); //最後のページnextを非表示
	return "list";
	
	}

//	@RequestMapping("/categories")
//	@ResponseBody
//	public List<Category> categories() {
//		return getCategories();
//	}
	
	
	
//	private List<Category> getCategories() {
//		@SuppressWarnings("unchecked")
//		List<Category> categories = (List<Category>) session.getAttribute("categories");
//		if (categories == null) {
//			categories = categoryService.findAllCategories();
//			session.setAttribute("categories", categories);
//		}
//		return categories;
//	}
	
	
//	private Category getCategoryByName(List<Category> categories, String categoryName) {
//		for (Category category : categories) {
//			if(category.getName().equals(categoryName)) {
//				return category;
//			}
//		}
//		return null;
//	}
	
	
	
	
	
	
	
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
