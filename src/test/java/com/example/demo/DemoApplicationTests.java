package com.example.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.RegistForm;
import com.example.demo.form.SearchForm;
import com.example.demo.service.SearchService;
import com.example.demo.utility.ValidationUtil;

@SpringBootTest
class DemoApplicationTests {
	private ModelAndView mav;
	private RegistForm form;
	private SearchForm sf;

	@BeforeEach
	public void initialize() {
		mav = new ModelAndView();
		form = new RegistForm();
		form.setName("aaaaabbbbbcccccddddd");
		form.setAreaCode("0123");
		form.setCityCode("0123");
		form.setSubscriberNumber("0123");
		sf = new SearchForm();
		sf.setKeyword("aaaaabbbbbcccccddddd");
	}

	@Test
	public void ValidationUtilTest() {
		ValidationUtil validationUtil = new ValidationUtil();
	}

	@Test
	public void isValidAtRegistOrUpdateTest() {
		assertThat(ValidationUtil.isValidAtRegistOrUpdate(form.getName(), form.getAreaCode(),
				form.getCityCode(), form.getSubscriberNumber(), mav)).isTrue();
		assertThat(ValidationUtil.isValidAtRegistOrUpdate("", form.getAreaCode(), form.getCityCode(),
				form.getSubscriberNumber(), mav)).isFalse();
	}

	@Test
	public void isValidNameTest() {
		assertThat(ValidationUtil.isValidName(form.getName(), mav)).isTrue();
		assertThat(ValidationUtil.isValidName("", mav)).isFalse();
		assertThat(ValidationUtil.isValidName(form.getName() + "a", mav)).isFalse();
	}

	@Test
	public void isValidPhoneNumberTest() {
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), form.getCityCode(),
				form.getSubscriberNumber(), mav)).isTrue();
		assertThat(ValidationUtil.isValidPhoneNumber("", form.getCityCode(), form.getSubscriberNumber(),
				mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), "", form.getSubscriberNumber(),
				mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), form.getCityCode(), "", mav))
				.isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode() + "0", form.getCityCode(),
				form.getSubscriberNumber(), mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), form.getCityCode() + "0",
				form.getSubscriberNumber(), mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), form.getCityCode(),
				form.getSubscriberNumber() + "0", mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber("０１２３", form.getCityCode(),
				form.getSubscriberNumber(), mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), "０１２３",
				form.getSubscriberNumber(), mav)).isFalse();
		assertThat(ValidationUtil.isValidPhoneNumber(form.getAreaCode(), form.getCityCode(),
				"０１２３", mav)).isFalse();
	}

	@Test
	public void isHalfNumericTest() {
		assertThat(ValidationUtil.isHalfNumeric(form.getAreaCode())).isTrue();
		assertThat(ValidationUtil.isHalfNumeric("０１２３")).isFalse();
	}

	@Test
	public void isValidTest() {
		assertThat(SearchService.isValid(sf.getKeyword())).isTrue();
		assertThat(SearchService.isValid(sf.getKeyword() + "a")).isFalse();
	}

}
