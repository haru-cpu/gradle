package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository//�f�[�^�x�[�X�𑀍삷��N���X���Ƃ������Ƃ�DI�R���e�i�ɒm�点��
public class InquiryDaoImpl implements InquiryDao {
	
	//�f�[�^�x�[�X����p�̃N���X
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired//JdbcTemplate��ǂݍ���
	public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {//DI�R���e�i�Ő������ꂽ�C���X�^���X���n����Ă���
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertInquiry(Inquiry inquiry){//id���΂������R�́AAutoIncrement�̂��߁A�w�肵�Ă͂����Ȃ�
		jdbcTemplate.update("INSERT INTO inquiry(name, email, contents, created) VALUES(?, ?, ?, ?)",
				 inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
			

	}

	@Override
	public List<Inquiry> getAll() {
		String sql = "SELECT id, name, email, contents, created FROM inquiry";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		//Map�̒��g��Inquiry�ɋl�ߒ������I�u�W�F�N�g�w��
		List<Inquiry> list = new ArrayList<Inquiry>();
		
		for(Map<String, Object> result : resultList) {
			Inquiry inquiry = new Inquiry();
			
			inquiry.setId((int)result.get("id"));//�Ԃ�l��Object�^�Ȃ̂ŁA�_�E���L���X�g�̕K�v������
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
