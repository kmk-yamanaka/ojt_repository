package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.RegistForm;
import com.example.demo.form.SearchForm;
import com.example.demo.form.UpdateForm;
import com.example.demo.service.RegistService;
import com.example.demo.service.SearchService;
import com.example.demo.service.UpdateService;

@Controller
public class PhoneBookController {
	@Autowired
	private SearchService search;
	@Autowired
	private RegistService regist;
	@Autowired
	private UpdateService update;

	/**トップページを表示*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView searchInit(ModelAndView mav) {
//		int pageNumber = 1;
		return search(new SearchForm(), mav);
	}

	/**検索ロジックを呼び出して検索ページへ遷移*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(SearchForm input, ModelAndView mav
//			,@RequestParam(value="pageNumber", required = true) int pageNumber
			) {
		int pageNumber = 1;
		search.execute(input, mav, pageNumber);
		return mav;
	}

	/**次のページを表示*/
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public ModelAndView next(@RequestParam(value="pageNumber", required = true) int pageNumber,
			@RequestParam(value="lastPageNumber", required = true) int lastPageNumber,
			SearchForm input, ModelAndView mav) {
		search.next(pageNumber, lastPageNumber, input, mav);
		return mav;
	}

	/**前のページを表示*/
	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public ModelAndView back(@RequestParam(value="pageNumber", required = true) int pageNumber,
			@RequestParam(value="lastPageNumber", required = true) int lastPageNumber,
			SearchForm input, ModelAndView mav) {
		search.back(pageNumber, lastPageNumber, input, mav);
		return mav;
	}

	/**削除処理*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(ModelAndView mav, SearchForm input,
			@RequestParam(value="id", required = true) int id,
//			@RequestParam(value="keyword", required = true) String keyword,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		//phoneBookRepository.delete(id);
		search.delete(mav, id, input, pageNumber);
		search.execute(input, mav, pageNumber);
		return mav;
//		return searchInit(mav);
	}

	/**エクスポート*/
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public ModelAndView export(ModelAndView mav, SearchForm input,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		search.export(input, mav, pageNumber);
		search.execute(input, mav, pageNumber);
		return mav;
	}

	/**登録画面へ遷移*/
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView registInit(ModelAndView mav,
			@RequestParam(value="keyword", required = true) String keyword,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		regist.registInit(mav, keyword, pageNumber);
		//mav.setViewName("regist");
		return mav;
	}

	/**登録処理*/
	@RequestMapping(value = "/registnew", method = RequestMethod.POST)
	public ModelAndView regist(RegistForm input, ModelAndView mav,
			@RequestParam(value="keyword", required = true) String keyword,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		regist.regist(input, mav, keyword, pageNumber);
		mav.setViewName("regist");
		return mav;
		//return registInit(mav);
	}

	/**更新画面へ遷移*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateInit(ModelAndView mav, @RequestParam(value="id", required = true) int id,
			@RequestParam(value="name", required = true) String name,
			@RequestParam(value="phoneNumber", required = true) String phoneNumber,
			@RequestParam(value="keyword", required = true) String keyword,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		update.updateInit(mav, id, name, phoneNumber, keyword, pageNumber);
		return mav;
	}

	/**更新処理*/
	@RequestMapping(value = "/updatenew", method = RequestMethod.POST)
	public ModelAndView update(UpdateForm input, ModelAndView mav,
			@RequestParam(value="id", required = true) int id,
			@RequestParam(value="keyword", required = true) String keyword,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		update.update(input, mav, id, keyword, pageNumber);
		mav.setViewName("update");
		return mav;
	}

	/**一覧画面に戻る*/
	@RequestMapping(value = "/backToSearch", method = RequestMethod.POST)
	public ModelAndView backToSearch(SearchForm input, ModelAndView mav,
			@RequestParam(value="pageNumber", required = true) int pageNumber) {
		search.execute(input, mav, pageNumber);
		return mav;
	}
}