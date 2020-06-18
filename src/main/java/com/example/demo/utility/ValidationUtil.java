package com.example.demo.utility;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.Message;

/**
 *入力された名前と電話番号にエラーがないかをチェックするクラス
 */
public class ValidationUtil {
	/**
	 * 入力された名前と電話番号をチェックするメソッド
	 * @param name 入力された名前
	 * @param inputPhoneNumber 入力された電話番号
	 * @param mav
	 * @param phoneBookList
	 * @return 正常ならtrue、異常ならfalse
	 * */
	public static boolean isValidAtRegistOrUpdate(String name, String areaCode, String cityCode,
			String subscriberNumber, ModelAndView mav) {
//		boolean isValid = false;
//		if(isValidName(inputName, mav) & isValidPhoneNumber(inputPhoneNumber, mav)) {
//			isValid = true;
//		}
		return isValidName(name, mav) & isValidPhoneNumber(areaCode, cityCode, subscriberNumber, mav);
	}

	private static boolean isValidName(String inputName, ModelAndView mav) {
		boolean isValidName = true;
		if(StringUtils.isEmpty(inputName)) {
			isValidName = false;
			mav.addObject("msg", Message.NAME_EMPTY);
		}else if(inputName.length() > Constants.NAME_MAX) {
			isValidName = false;
			mav.addObject("msg", Message.NAME_LIMIT);
		}
		return isValidName;
	}

	private static boolean isValidPhoneNumber(String areaCode, String cityCode, String subscriberNumber,
			ModelAndView mav) {
		boolean isValidPhoneNumber = true;
		if("".equals(areaCode) || "".equals(cityCode) || "".equals(subscriberNumber)) {
			isValidPhoneNumber = false;
			mav.addObject("msg2", Message.PHONENUMBER_EMPTY);
		}else if(areaCode.length() > Constants.PHONENUMBER_MAX || cityCode.length() > Constants.PHONENUMBER_MAX ||
				subscriberNumber.length() > Constants.PHONENUMBER_MAX) {
			isValidPhoneNumber = false;
			mav.addObject("msg2", Message.PHONENUMBER_LIMIT);
		}else if(!isHalfNumeric(areaCode) || !isHalfNumeric(cityCode) || !isHalfNumeric(subscriberNumber)){
			isValidPhoneNumber = false;
			mav.addObject("msg2", Message.PHONENUMBER_FAULT);
		}

		return isValidPhoneNumber;
	}

	private static boolean isHalfNumeric(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }
}