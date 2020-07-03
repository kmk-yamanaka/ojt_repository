package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.RegistForm;
import com.example.demo.utility.Constants;
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
	private PhoneBookRepository phoneBookRepository;

	public void registInit(ModelAndView mav, String keyword, int pageNumber) {
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("regist");
	}

	public void regist(RegistForm input, ModelAndView mav, String keyword, int pageNumber) {
		String name = input.getName();
		String areaCode = input.getAreaCode();
		String cityCode = input.getCityCode();
		String subscriberNumber = input.getSubscriberNumber();

		name = HandleSpace.handleSpaceName(name); // 空白処理メソッドの呼び出し
		areaCode = HandleSpace.deleteSpacePhoneNumber(areaCode);
		cityCode = HandleSpace.deleteSpacePhoneNumber(cityCode);
		subscriberNumber = HandleSpace.deleteSpacePhoneNumber(subscriberNumber);

		boolean isValid = ValidationUtil.isValidAtRegistOrUpdate(name, areaCode, cityCode,
				subscriberNumber, mav);
		if (isValid) {
			String phoneNumber = areaCode + Constants.HYPHEN + cityCode + Constants.HYPHEN + subscriberNumber;
			phoneBookRepository.regist(name, phoneNumber);
			mav.addObject("msg", Message.REGIST);
		}

		mav.addObject("name", name);
		mav.addObject("areaCode", areaCode);
		mav.addObject("cityCode", cityCode);
		mav.addObject("subscriberNumber", subscriberNumber);
		mav.addObject("isValid", isValid);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
	}

}
