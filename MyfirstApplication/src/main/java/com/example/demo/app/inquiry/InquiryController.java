package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryNotFoundException;
import com.example.demo.service.InquiryService;

@Controller
@RequestMapping("/inquiry")//コントローラーを訪れるためのURL
public class InquiryController {
	
	private final InquiryService inquiryService;
	
	@Autowired
	public InquiryController(InquiryService inquiryService) {
		this.inquiryService = inquiryService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Inquiry> list = inquiryService.getAll();
		
		/*
		Inquiry inquiry = new Inquiry();
		inquiry.setId(4);
		inquiry.setName("Jamie");
		inquiry.setEmail("sample@example.com");
		inquiry.setContents("Hello");
		
		inquiryService.update(inquiry);

		
		try {
			inquiryService.update(update);
		}catch(InquiryNotFoundException e) {
			model.addAttribute("message", e);
			return "error/CustomPage";
		}
*/		
		model.addAttribute("inquiryList", list);
		model.addAttribute("title", "Inquiry Index");
		
		return "inquiry/index_boot";
	}

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm, //Formの初期化された情報が格納
			Model model,
			//フラッシュスコープを受け取る時のアノテーション@ModelAtrribute
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	@PostMapping("/form")
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	//Postで送信した場合に遷移
	@PostMapping("/confirm")
	public String confirm(@Validated InquiryForm inquiryForm,//emailやnameのチェック
			BindingResult result,//Validateをかけた結果（InquiryForm）がBindingResult型で返ってくるboolean
			Model model) {
		if(result.hasErrors()) {//エラーがあった場合ホームのページに戻す
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form_boot";
		}
		//エラーがなかった場合
		model.addAttribute("title", "Confirm Page");
		return "inquiry/confirm_boot";
	}
	
	@PostMapping("/complete")//確認ページ
	public String complete(@Validated InquiryForm inquiryForm,//確認フォームの内容は裏からいじれるためバリデーションする
			BindingResult result,
			Model model,
			//フラッシュスコープ
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "InquiryForm");
			return "inquiry/form_boot";
		}
		
		Inquiry inquiry = new Inquiry();
		
		inquiry.setName(inquiryForm.getName());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setContents(inquiryForm.getContents());
		inquiry.setCreated(LocalDateTime.now());
		
		inquiryService.save(inquiry);
		
		
		//フラッシュスコープ
		//Regisstered!が1回表示されるとセッションのデータが破棄される＝フラッシュスコープ
		redirectAttributes.addFlashAttribute("complete", "Registered!");
		//redirect:/inquiry/form←htmlファイルを指しているのではなく、urlを指している
		//クライアントにレスポンスを返し、クライアントからリクエストを受ける
		//リクエストスコープではデータとつなぐことができない
		return "redirect:/inquiry/form";
	}
	/*
	@ExceptionHandler(InquiryNotFoundException.class)
	public String handleException(InquiryNotFoundException e, Model model) {
		model.addAttribute("message", e);
		return "error/CusomPage";
	}
			*/
	
}
