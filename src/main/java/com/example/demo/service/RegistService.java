package com.example.demo.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.RegistForm;
import com.example.demo.utility.HandleSpace;
import com.example.demo.utility.ValidationUtil;

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

		name = HandleSpace.handleSpaceName(name); // 空白処理メソッドの呼び出し
		areaCode = HandleSpace.deleteSpacePhoneNumber(areaCode);
		cityCode = HandleSpace.deleteSpacePhoneNumber(cityCode);
		subscriberNumber = HandleSpace.deleteSpacePhoneNumber(subscriberNumber);

		if(ValidationUtil.isValidAtRegistOrUpdate(name, areaCode, cityCode, subscriberNumber, mav)) {
			String phoneNumber = areaCode + "-" + cityCode + "-" + subscriberNumber;
			phoneBookRepository.regist(name, phoneNumber);
			mav.addObject("msg", Message.REGIST);
		}

		mav.addObject("name", name);
		mav.addObject("areaCode", areaCode);
		mav.addObject("cityCode", cityCode);
		mav.addObject("subscriberNumber", subscriberNumber);
	}

}
