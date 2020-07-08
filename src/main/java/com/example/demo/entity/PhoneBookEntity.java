package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * データ転送オブジェクト
 * */
@Entity
@Table(name = "phone_book")
public class PhoneBookEntity {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_book_id")
	private int id;
	/**
	 * 名前
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 電話番号
	 */
	@Column(name = "phone_number")
	private String phoneNumber;
	/**
	 * 削除フラグ
	 */
	@Column(name = "is_delete")
	private boolean isDelete;
	/**
	 * 都道府県
	 */
	@Column(name = "prefecture")
	private String prefecture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
}