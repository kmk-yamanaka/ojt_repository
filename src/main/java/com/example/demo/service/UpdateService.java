package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PhoneBookRepository;

/**
 * 更新クラス
 * @author Owner
 *
 */
@Service
public class UpdateService {
	@Autowired
	private PhoneBookRepository phoneBookRepository;
	
	public void registInit() {
		
	}

}
