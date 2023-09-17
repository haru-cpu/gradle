package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Survey;

@Repository//�f�[�^�x�[�X�������N���X�ł���Ƃ������Ƃ�DI�R���e�i�ɒm�点��
public class SurveyDaoImpl implements SurveyDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired//DI�R���e�i����C���X�^���X���擾���邽�߁�DI�R���e�i����jdbcTemplate�C���X�^���X���擾����
	public SurveyDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertSurvey(Survey survey) {
		jdbcTemplate.update("INSERT INTO survey(age, satisfaction, comment, created) VALUES(?, ?, ?, ?)",
				survey.getAge(), survey.getSatisfaction(), survey.getComment(), survey.getCreated());

	}

	@Override
	public List<Survey> getAll() {//entity�Ƀf�[�^���l�߂�
		String sql = "SELECT id, age, satisfaction, comment, created FROM survey";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		
		List<Survey> list = new ArrayList<Survey>();
		
		for(Map<String, Object> result : resultList) {
			Survey survey = new Survey();
			survey.setId((int)result.get("id"));
			survey.setAge((int)result.get("age"));
			survey.setSatisfaction((int)result.get("satisfaction"));
			survey.setComment((String)result.get("comment"));
			survey.setCreated(((Timestamp)result.get("created")).toLocalDateTime());//Date�^���������̂����o����Timestamp�^�ɂȂ遨�t�B�[���h�ł�LocalDateTime�^
			
			list.add(survey);
		}
		
		return list;
	}

}
