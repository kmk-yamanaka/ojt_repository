package com.example.demo.utility;

/**空白処理クラス*/
public class HandleSpace {

	/**名前の空白処理
	 * 全角スペースを半角スペースに変換し、前後のスペースのみ削除する。
	 * @param name
	 * @return
	 */
	public static String handleSpaceName(String name) {
		if (name != null) {
			name = name.replace(Constants.FULL_SPACE, Constants.HALF_SPACE).trim();
		}
		return name;
	}

	/**
	 * 電話番号の空白処理
	 * 全角スペースを半角スペースに変換し、全てのスペースを削除する。
	 * @param phoneNumber
	 * @return
	 */
	public static String deleteSpacePhoneNumber(String phoneNumber) {
		if (phoneNumber != null) {
			phoneNumber = phoneNumber.replace(Constants.FULL_SPACE, Constants.HALF_SPACE)
					.replace(Constants.HALF_SPACE, "");
		}
		return phoneNumber;
	}

}
