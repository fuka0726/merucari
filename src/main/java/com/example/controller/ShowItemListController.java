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
import com.example.domein.Item;
import com.example.form.SearchForm;
import com.example.service.CategoryService;
import com.example.service.ShowItemListService;

/**
 * @author fuka
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {
	
	public static final int ROW_PAR_PAGE = 30;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@ModelAttribute
	private SearchForm setupForm() {
		return new SearchForm();
	}
	
	
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
	
	setCategoryIds(searchForm, getCategories());
	System.out.println(searchForm);
	List<Item> itemList = showItemListService.search(searchForm);
	
	//それぞれバリューに名前をつける
	model.addAttribute("itemList", itemList);
	model.addAttribute("maxPage", maxPage);
	model.addAttribute("nowPage", searchForm.getPage()); //最初のページでprevボタンを非表示にさせるため名前をつける
	model.addAttribute("lastPage", searchForm.getPage()); //最後のページnextを非表示
	return "list";
	
	}

	@RequestMapping("/categories")
	@ResponseBody
	public List<Category> categories() {
		return getCategories();
	}
	
	
	
	/**
	 * 全カテゴリー情報を取得する.
	 * 
	 * @return　カテゴリー
	 */
	private List<Category> getCategories() {
		@SuppressWarnings("unchecked")
		//カテゴリーをセッションに保存
		List<Category> categories = (List<Category>) session.getAttribute("categories");
//		List<Category> categories = categoryService.findAllCategories();
		//　もしnullならカテゴリー情報をDBから取得する
		if (categories == null) {
			categories = categoryService.findAllCategories();
			session.setAttribute("categories", categories);
		}
		return categories;
	}
	
	
	
	   /**
     * 検索完了時、カテゴリーのプルダウンを維持するために
     * categoryNameから、daiCategoryId, chuCategoryId, syoCategoryId を求めてフォームにセットする.
     *
     * @param searchForm
     * @param categories
     */
    private void setCategoryIds(SearchForm searchForm, List<Category> categories) {
        // 一旦全てクリアーする
        searchForm.setDaiCategoryId(null);
        searchForm.setChuCategoryId(null);
        searchForm.setSyoCategoryId(null);
        if (searchForm.getCategoryName() != null) {
            String[] categoryArray = searchForm.getCategoryName().split("/");
            if (categoryArray.length >= 1 && !"".equals(categoryArray[0])) {
                Category daiCategory = getCategoryByName(categories, categoryArray[0]);
                searchForm.setDaiCategoryId(daiCategory.getId());
                if (categoryArray.length >= 2) {
                    Category chuCategory = getCategoryByName(daiCategory.getChildCategories(), categoryArray[1]);
                    searchForm.setChuCategoryId(chuCategory.getId());
                    if (categoryArray.length >= 3) {
                        Category syoCategory = getCategoryByName(chuCategory.getChildCategories(), categoryArray[2]);
                        searchForm.setSyoCategoryId(syoCategory.getId());
                    }
                }
            }
        }
    }
	

	private Category getCategoryByName(List<Category> categories, String categoryName) {
	for (Category category : categories) {
		if(category.getName().equals(categoryName)) {
			return category;
		}
	}
	return null;
}

	
	

}
