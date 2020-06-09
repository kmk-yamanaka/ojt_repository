package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.RegistForm;
import com.example.demo.form.SearchForm;
import com.example.demo.service.RegistService;
import com.example.demo.service.SearchService;

@Controller
public class PhoneBookController {
	@Autowired
	private SearchService search;
	@Autowired
	private RegistService regist;

	/**トップページを表示*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		return search(new SearchForm(), mav);
	}

	/**検索ロジックを呼び出して検索ページへ遷移*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(SearchForm input, ModelAndView mav) {
		search.execute(input, mav);
		return mav;
	}

	/**登録画面へ遷移*/
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView registInit(ModelAndView mav) {
		mav.setViewName("regist");
		return mav;
	}

	/**登録処理*/
	@RequestMapping(value = "/regist/new", method = RequestMethod.POST)
	public ModelAndView regist(RegistForm input, ModelAndView mav) {
		regist.regist(input, mav);
		mav.setViewName("regist");
		return mav;
	}

	/**更新画面へ遷移*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateInit(ModelAndView mav) {
		mav.setViewName("update");
		return mav;
	}
}