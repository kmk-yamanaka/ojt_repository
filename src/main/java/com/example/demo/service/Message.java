package com.example.demo.service;

/**対応したメッセージを定義したクラス*/
public class Message {
	/**検索にヒットした際に出すメッセージ*/
	public static final String SEARCH_HIT_COUNT = "件ヒットしました";

	/**検索にヒットしなかった際に出すメッセージ*/
	public static final String SEARCH_NOT_HIT = "検索結果はありません";

	/**入力した名前が空の場合に出すメッセージ*/
	public static final String NAME_EMPTY = "名前を入力してください";

	/**入力した電話番号が空の場合に出すメッセージ*/
	public static final String PHONENUMBER_EMPTY = "電話番号を全て入力してください";

	/**入力された名前が21文字以上だった場合に出すメッセージ*/
	public static final String NAME_LIMIT = "名前は20文字以内で入力してください";

	/**入力された電話番号が5文字以上だった場合に出すメッセージ*/
	public static final String PHONENUMBER_LIMIT = "電話番号は入力欄ごとに4桁以内で入力してください";

	/**入力された電話番号が不正な場合に出すメッセージ*/
	public static final String PHONENUMBER_FAULT = "電話番号は半角数字で入力してください";

	/**データを削除した際に出すメッセージ*/
	public static final String DELETE = "削除が完了しました";

	/**データを登録した際に出すメッセージ*/
	public static final String REGIST = "登録が完了しました。";

	/**データを更新した際に出すメッセージ*/
	public static final String UPDATE = "更新が完了しました。";

}
