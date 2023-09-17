package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository//データベースを操作するクラスだということをDIコンテナに知らせる
public class InquiryDaoImpl implements InquiryDao {
	
	//データベース操作用のクラス
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired//JdbcTemplateを読み込む
	public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {//DIコンテナで生成されたインスタンスが渡されてくる
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertInquiry(Inquiry inquiry){//idを飛ばした理由は、AutoIncrementのため、指定してはいけない
		jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
				 inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
			

	}

	@Override
	public List<Inquiry> getAll() {
		String sql = "SELECT id, name, email, contents, created FROM inquiry";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		//Mapの中身をInquiryに詰め直す→オブジェクト指向
		List<Inquiry> list = new ArrayList<Inquiry>();
		
		for(Map<String, Object> result : resultList) {
			Inquiry inquiry = new Inquiry();
			
			inquiry.setId((int)result.get("id"));//返り値がObject型なので、ダウンキャストの必要がある
			inquiry.setName((String)result.get("name"));
			inquiry.setEmail((String)result.get("email"));
			inquiry.setContents((String)result.get("contents"));
			inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			
			list.add(inquiry);
		}
		
		return list;
	}

	@Override
	public int updateInquiry(Inquiry inquiry) {
		return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?, contents = ? WHERE id = ?",
				inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getId());
	}

}
