package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	private final SurveyService surveyService;
	
	@Autowired
	public SurveyController(SurveyService suveyService) {
		this.surveyService = suveyService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Survey> list = surveyService.getAll();
		
		model.addAttribute("surveyList", list);
		model.addAttribute("title", "Survey Index");
		
		return "survey/index_boot";
	}
	
	@GetMapping("/form")//最初にアクセス
	public String form(SurveyForm surveyForm,
			Model model,
			@ModelAttribute("/complete")String complete) {
		model.addAttribute("title", "Survey Form");
		return "survey/form_boot";
	}
	
	@PostMapping("/form")
	public String formGoBack(SurveyForm surveyForm,Model model) {
		model.addAttribute("title", "Survey Form");
		return "survey/form_boot";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Survey Form");
		return "survey/form_boot";
	}
	model.addAttribute("title", "Confirm Page");
	return "survey/confirm_boot";
	}
	
	@PostMapping("/complete")
	public String complete(@Validated SurveyForm surveyForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "SurveyForm");
		return "survey/form_boot";
	}
		
		Survey survey = new Survey();
		
		survey.setAge(surveyForm.getAge());
		survey.setSatisfaction(surveyForm.getSatisfaction());
		survey.setComment(surveyForm.getComment());
		survey.setCreated(LocalDateTime.now());
		
		//データベースの登録された後にリダイレクトされたページに戻る
		surveyService.save(survey);
		
	
	redirectAttributes.addFlashAttribute("complete", "Registered!");
	return "redirect:/survey/form";
}
}
