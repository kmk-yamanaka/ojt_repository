package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.UpdateForm;
import com.example.demo.utility.Constants;
import com.example.demo.utility.HandleSpace;
import com.example.demo.utility.ValidationUtil;

/**
 * 更新クラス
 * @author Owner
 *
 */
@Service
public class UpdateService {
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	public void updateInit(ModelAndView mav, @RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber
			, String keyword, int pageNumber) {

		String[] phoneNumbers = phoneNumber.split(Constants.HYPHEN, 0);
		String areaCode = phoneNumbers[0];
		String cityCode = phoneNumbers[1];
		String subscriberNumber = phoneNumbers[2];

		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("areaCode", areaCode);
		mav.addObject("cityCode", cityCode);
		mav.addObject("subscriberNumber", subscriberNumber);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("update");
	}

	public void update(UpdateForm input, ModelAndView mav,
			@RequestParam(value = "id", required = true) int id
			, String keyword, int pageNumber) {
		String name = input.getName();
		String areaCode = input.getAreaCode();
		String cityCode = input.getCityCode();
		String subscriberNumber = input.getSubscriberNumber();

		name = HandleSpace.handleSpaceName(name); // 空白処理メソッドの呼び出し
		areaCode = HandleSpace.deleteSpacePhoneNumber(areaCode);
		cityCode = HandleSpace.deleteSpacePhoneNumber(cityCode);
		subscriberNumber = HandleSpace.deleteSpacePhoneNumber(subscriberNumber);

		if (ValidationUtil.isValidAtRegistOrUpdate(name, areaCode, cityCode, subscriberNumber, mav)) {
			String phoneNumber = areaCode + Constants.HYPHEN + cityCode + Constants.HYPHEN + subscriberNumber;
			phoneBookRepository.update(name, phoneNumber, id);
			mav.addObject("msg", Message.UPDATE);
		}

		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("areaCode", areaCode);
		mav.addObject("cityCode", cityCode);
		mav.addObject("subscriberNumber", subscriberNumber);
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
	}

}
