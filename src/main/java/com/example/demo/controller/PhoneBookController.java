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
		return search(new SearchForm(), mav);
	}

	/**検索ロジックを呼び出して検索ページへ遷移*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(SearchForm input, ModelAndView mav) {
		search.execute(input, mav);
		return mav;
	}

	/**削除処理*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(ModelAndView mav, @RequestParam(value="id", required = true) int id) {
		//phoneBookRepository.delete(id);
		search.delete(id);
		return searchInit(mav);
	}

	/**登録画面へ遷移*/
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView registInit(ModelAndView mav) {
		mav.setViewName("regist");
		return mav;
	}

	/**登録処理*/
	@RequestMapping(value = "/registnew", method = RequestMethod.POST)
	public ModelAndView regist(RegistForm input, ModelAndView mav) {
		regist.regist(input, mav);
		//mav.setViewName("search");
		return searchInit(mav);
	}

	/**更新画面へ遷移*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateInit(ModelAndView mav, @RequestParam(value="id", required = true) int id,
			@RequestParam(value="name", required = true) String name,
			@RequestParam(value="phoneNumber", required = true) String phoneNumber) {
		update.updateInit(mav, id, name, phoneNumber);
		return mav;
	}

	/**更新処理*/
	@RequestMapping(value = "/updatenew", method = RequestMethod.POST)
	public ModelAndView update(UpdateForm input, ModelAndView mav,
			@RequestParam(value="id", required = true) int id) {
		update.update(input, mav, id);
		return searchInit(mav);
	}
}