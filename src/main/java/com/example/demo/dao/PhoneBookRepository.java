package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PhoneBookEntity;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBookEntity, Long> {

	/**検索SQL*/
	@Query(value = "SELECT p.phone_book_id, p.name, p.phone_number, false as is_delete, p.prefecture FROM phone_book p WHERE is_delete = false ORDER BY phone_book_id", nativeQuery = true)
	public List<PhoneBookEntity> findAll();

	/**検索SQL*/
	@Query(value = "SELECT p.phone_book_id, p.name, p.phone_number, false as is_delete, p.prefecture FROM phone_book p WHERE p.name LIKE %:keyword% AND is_delete = false ORDER BY phone_book_id", nativeQuery = true)
	public List<PhoneBookEntity> findResult(@Param("keyword") String keyword);

	/**削除SQL*/
	@Modifying
	@Transactional
	@Query(value = "UPDATE phone_book SET is_delete = true WHERE phone_book_id = :id", nativeQuery = true)
	public void delete(@Param("id") int id);

	/**登録SQL*/
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO phone_book (name,phone_number,is_delete) VALUES (:name,:phoneNumber,false)", nativeQuery = true)
	public void regist(@Param("name") String name, @Param("phoneNumber") String phoneNumber);

	/**更新SQL*/
	@Modifying
	@Transactional
	@Query(value = "UPDATE phone_book SET name = :name, phone_number = :phoneNumber WHERE phone_book_id = :id", nativeQuery = true)
	public void update(@Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("id") int id);
}