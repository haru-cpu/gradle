package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//ユーザの入力値を取得して、HTMLの表示
@RequestMapping("/sample")//sample→ドメイン以降のurl
public class SampleController {
	
	//jdbcを操作するクラス
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired//インスタンスを Spring 管理下におくため、クラスに付けるアノテーション
	//コンテナが自動生成したjdbcTemplateが引数として渡される
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	/*
	@GetMapping("/test")// sample/test 　urlでデータを渡す
	public String test(Model model) {//String→htmlのファイル名
		String sql = "SELECT id, name, email FROM inquiry WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Inquiry Form");//htmlに渡すデータ　titleをキーにして、Inquiry Formを渡す
		
		//sqlのデータをhtmlに渡している
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		return "test";//viewクラスに渡す
	}
	
	
	@GetMapping("/test")// sample/test 　urlでデータを渡す
	public String test(Model model) {//String→htmlのファイル名
		String sql = "SELECT id, age, satisfaction FROM survey WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Survey Form");//htmlに渡すデータ　titleをキーにして、Inquiry Formを渡す
		
		//sqlのデータをhtmlに渡している
		model.addAttribute("age", map.get("age"));
		model.addAttribute("satisfaction", map.get("satisfaction"));
		return "test";//viewクラスに渡す
	

}
	*/
	
	@GetMapping("/test")// sample/test 　urlでデータを渡す
	public String test(Model model) {//String→htmlのファイル名
		String sql = "SELECT id, age, satisfaction FROM survey WHERE id = 1";
		
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		
		
		model.addAttribute("title", "Survey Form");//htmlに渡すデータ　titleをキーにして、Inquiry Formを渡す
		
		//sqlのデータをhtmlに渡している
		model.addAttribute("age", map.get("age"));
		model.addAttribute("satisfaction", map.get("satisfaction"));
		return "test";//viewクラスに渡す
		
       

}
	
}







