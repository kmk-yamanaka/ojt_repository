package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.entity.PhoneBookEntity;
import com.example.demo.form.SearchForm;
import com.example.demo.form.SearchResultForm;
import com.example.demo.utility.Constants;
import com.example.demo.utility.HandleSpace;

/**
 * 検索クラス
 */
@Service
public class SearchService {
	@Autowired
	private HttpSession session;
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	/**入力された名前と電話帳リストにある名前を比較して合致するものをsessionに格納するメソッド*/
	public void execute(SearchForm input, ModelAndView mav, int pageNumber) {
		List<PhoneBookEntity> phoneBookList = new ArrayList<>();
		String keyword = input.getKeyword(); //入力された名前を取得
		keyword = HandleSpace.handleSpaceName(keyword); // 空白処理メソッドの呼び出し
		if (StringUtils.isEmpty(keyword) || !isValid(keyword)) { // キーワードが空白(null)or入力チェック
			phoneBookList = phoneBookRepository.findAll(); // に引っかかる場合は初期表示と同じ処理をする
		} else {
			phoneBookList = phoneBookRepository.findResult(keyword);
			//pageNumber = 1;
		}
		session.setAttribute("phoneBookList", phoneBookList);

		//pageNumber = 1;
		//int pageNumber = 1;
		double lastPageNum = Math.ceil((double) phoneBookList.size() / Constants.DATA_DISPLAYED_MAX);
		int lastPageNumber = (int) lastPageNum;
//		if (lastPageNumber == 0) {
//			lastPageNumber++;
//		}
		page(pageNumber, lastPageNumber, input, mav);
	}

	/**
	 * ページング処理メソッド
	 * @param pageNumber
	 * @param lastPageNumber
	 * @param input
	 * @param mav
	 */
	private void page(int pageNumber, int lastPageNumber, SearchForm input, ModelAndView mav) {
		List<PhoneBookEntity> phoneBookList = new ArrayList<>();
		phoneBookList = (List<PhoneBookEntity>) session.getAttribute("phoneBookList");
		List<SearchResultForm> searchList = new ArrayList<>();
//		if(pageNumber == 0) {
//			pageNumber = 1;
//		}
		int firstIndex = Constants.DATA_DISPLAYED_MAX * (pageNumber - 1);
		if(pageNumber == 0) {
			firstIndex = 0;
		}
//		if(firstIndex > phoneBookList.size()) {
//			pageNumber = 1;
//			firstIndex = 0;
//		}
		int max = firstIndex + Constants.DATA_DISPLAYED_MAX;
		for (int i = firstIndex; i < max; i++) {
			if (i == phoneBookList.size()) {
				break;
			}
			PhoneBookEntity entity = phoneBookList.get(i);
			SearchResultForm sf = new SearchResultForm();
			sf.setId(entity.getId());
			sf.setName(entity.getName());
			sf.setPhoneNumber(entity.getPhoneNumber());
			searchList.add(sf);
		}

		String keyword = input.getKeyword();
		mav.addObject("keyword", keyword);
		mav.addObject("searchList", searchList);
		if(lastPageNumber == 0) {
			pageNumber = 0;
		}
		boolean isInitial = pageNumber <= 1 && StringUtils.isEmpty(keyword);
		mav.addObject("isInitial", isInitial);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("lastPageNumber", lastPageNumber);
		mav.setViewName("search");
		SearchService.searchMsg(searchList, phoneBookList, pageNumber, lastPageNumber, input, mav);
	}

	/**
	 * 次ページメソッド
	 * @param pageNumber
	 * @param lastPageNumber
	 * @param input
	 * @param mav
	 */
	public void next(int pageNumber, int lastPageNumber, SearchForm input, ModelAndView mav) {
		if (pageNumber != lastPageNumber) {
			pageNumber++;
		}
//		String keyword = input.getKeyword();
//		mav.addObject("keyword", keyword);
		page(pageNumber, lastPageNumber, input, mav);
	}

	/**
	 * 前ページメソッド
	 * @param pageNumber
	 * @param lastPageNumber
	 * @param input
	 * @param mav
	 */
	public void back(int pageNumber, int lastPageNumber, SearchForm input, ModelAndView mav) {
		if (pageNumber != 1) {
			pageNumber--;
		}
		page(pageNumber, lastPageNumber, input, mav);
	}

	/**
	 * 入力桁数チェックメソッド
	 * @param inputName
	 * @param mav
	 * @return 正常ならtrue、異常ならfalse
	 */
	public static boolean isValid(String inputName) {
		return inputName.length() <= Constants.NAME_MAX;
	}

	/**
	 * メッセージを画面に埋め込むメソッド
	 * @param searchList
	 * @param inputName
	 * @param mav
	 */
	private static void searchMsg(List<SearchResultForm> searchList, List<PhoneBookEntity> phoneBookList,
			int pageNumber, int lastPageNumber, SearchForm input, ModelAndView mav) {
		mav.addObject("dataNumberMsg", searchList.size() + Message.DISPLAYED_COUNT + phoneBookList.size()
				+ Message.TOTAL_COUNT);
		mav.addObject("pageNumberMsg", pageNumber + Message.PAGE_NUMBER + lastPageNumber
				+ Message.LAST_PAGE_NUMBER);
		String inputName = input.getKeyword();
		if (inputName != null && !isValid(inputName)) {
			mav.addObject("msg", Message.NAME_LIMIT);
		}
	}

	/**
	 * 削除メソッド
	 * @param id
	 */
	public void delete(ModelAndView mav, int id, SearchForm input, int pageNumber) {
		phoneBookRepository.delete(id);
		mav.addObject("msg", Message.DELETE);
		String keyword = input.getKeyword();
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
	}

	public void export(SearchForm input, ModelAndView mav, int pageNumber) {
		List<PhoneBookEntity> phoneBookList = new ArrayList<>();
		phoneBookList = phoneBookRepository.findAll();

		Map<String, Integer> prefectureCount = new HashMap<>();

		for(PhoneBookEntity phoneBook:phoneBookList) {
			int count = 0;
			String prefecture = phoneBook.getPrefecture();
			if(prefectureCount.containsKey(prefecture)) {
				count = prefectureCount.get(prefecture);
			}
			prefectureCount.put(prefecture, count + 1);
		}

		prefectureCount.forEach((prefecture, count) -> {
			System.out.println(prefecture + "," + count);
		});

		String keyword = input.getKeyword();
		mav.addObject("keyword", keyword);
		mav.addObject("pageNumber", pageNumber);
	}

}