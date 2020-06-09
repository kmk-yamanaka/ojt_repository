package com.example.demo.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.RegistForm;

/**
 * 登録クラス
 * @author Owner
 *
 */
@Service
public class RegistService {
	@Autowired
	private HttpSession session;
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	public void regist(RegistForm input, ModelAndView mav) {
		String name = input.getName();
		String areaCode = input.getAreaCode();
		String cityCode = input.getCityCode();
		String subscriberNumber = input.getSubscriberNumber();
		String phoneNumber = areaCode + "-" + cityCode + "-" + subscriberNumber;

		phoneBookRepository.regist(name, phoneNumber);
	}

}
