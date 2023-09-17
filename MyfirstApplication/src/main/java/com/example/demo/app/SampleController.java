package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//���[�U�̓��͒l���擾���āAHTML�̕\��
@RequestMapping("/sample")//sample���h���C���ȍ~��url
public class SampleController {
	
	//jdbc�𑀍삷��N���X
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired//�C���X�^���X�� Spring �Ǘ����ɂ������߁A�N���X�ɕt����A�m�e�[�V����
	//�R���e�i��������������jdbcTemplate�������Ƃ��ēn�����
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	/*
	@GetMapping("/test")// sample/test �@url�Ńf�[�^��n��
	public String test(Model model) {//String��html�̃t�@�C����
		String sql = "SELECT id, name, email FROM inquiry WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Inquiry Form");//html�ɓn���f�[�^�@title���L�[�ɂ��āAInquiry Form��n��
		
		//sql�̃f�[�^��html�ɓn���Ă���
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		return "test";//view�N���X�ɓn��
	}
	
	
	@GetMapping("/test")// sample/test �@url�Ńf�[�^��n��
	public String test(Model model) {//String��html�̃t�@�C����
		String sql = "SELECT id, age, satisfaction FROM survey WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Survey Form");//html�ɓn���f�[�^�@title���L�[�ɂ��āAInquiry Form��n��
		
		//sql�̃f�[�^��html�ɓn���Ă���
		model.addAttribute("age", map.get("age"));
		model.addAttribute("satisfaction", map.get("satisfaction"));
		return "test";//view�N���X�ɓn��
	

}
	*/
	
	@GetMapping("/test")// sample/test �@url�Ńf�[�^��n��
	public String test(Model model) {//String��html�̃t�@�C����
		String sql = "SELECT id, age, satisfaction FROM survey WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Survey Form");//html�ɓn���f�[�^�@title���L�[�ɂ��āAInquiry Form��n��
		
		//sql�̃f�[�^��html�ɓn���Ă���
		model.addAttribute("age", map.get("age"));
		model.addAttribute("satisfaction", map.get("satisfaction"));
		return "test";//view�N���X�ɓn��
		
       

}
	
}







