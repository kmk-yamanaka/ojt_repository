package com.example.demo.service;

/**対応したメッセージを定義したクラス*/
public class Message {
	/**検索にヒットした際に出すメッセージ*/
	public static final String SEARCH_HIT_COUNT = "件ヒットしました。";

	/**検索にヒットしなかった際に出すメッセージ*/
	public static final String SEARCH_NOT_HIT = "検索結果はありません。";

	/**入力した値が空の場合に出すメッセージ*/
	public static final String INPUT_EMPTY = "名前または電話番号を入力してください。";

	/**入力された名前が20文字以上だった場合に出すメッセージ*/
	public static final String NAME_LIMIT = "名前は20文字以内で入力してください。";

	/**入力された電話番号が不正な場合に出すメッセージ*/
	public static final String PHONENUMBER_FAULT = "電話番号が不正です。(ハイフンを入れて)";

	/**入力された電話番号が重複している場合に出すメッセージ*/
	public static final String PHONENUMBER_DOUBLE = "電話番号が重複しています。";

	/**アカウント登録や変更が正常に行われた際に出すメッセージ*/
	public static final String SUCCESS_ACCOUNT = "さんを登録しました。";

	/**アカウント削除が正常に行われた際に出すメッセージ*/
	public static final String DELETE_ACCOUNT = "アカウントを削除しました。";

	/**電話番号を削除した際に出すメッセージ*/
	public static final String DELETE = "さんを削除しました。";

}
