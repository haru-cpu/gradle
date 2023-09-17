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
@RequestMapping("/inquiry")//�R���g���[���[��K��邽�߂�URL
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
	public String form(InquiryForm inquiryForm, //Form�̏��������ꂽ��񂪊i�[
			Model model,
			//�t���b�V���X�R�[�v���󂯎�鎞�̃A�m�e�[�V����@ModelAtrribute
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	@PostMapping("/form")
	public String formGoBack(InquiryForm inquiryForm, Model model) {
		model.addAttribute("title", "Inquiry Form");
		return "inquiry/form_boot";
	}
	
	//Post�ő��M�����ꍇ�ɑJ��
	@PostMapping("/confirm")
	public String confirm(@Validated InquiryForm inquiryForm,//email��name�̃`�F�b�N
			BindingResult result,//Validate�����������ʁiInquiryForm�j��BindingResult�^�ŕԂ��Ă���boolean
			Model model) {
		if(result.hasErrors()) {//�G���[���������ꍇ�z�[���̃y�[�W�ɖ߂�
			model.addAttribute("title", "Inquiry Form");
			return "inquiry/form_boot";
		}
		//�G���[���Ȃ������ꍇ
		model.addAttribute("title", "Confirm Page");
		return "inquiry/confirm_boot";
	}
	
	@PostMapping("/complete")//�m�F�y�[�W
	public String complete(@Validated InquiryForm inquiryForm,//�m�F�t�H�[���̓��e�͗����炢����邽�߃o���f�[�V��������
			BindingResult result,
			Model model,
			//�t���b�V���X�R�[�v
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
		
		
		//�t���b�V���X�R�[�v
		//Regisstered!��1��\�������ƃZ�b�V�����̃f�[�^���j������遁�t���b�V���X�R�[�v
		redirectAttributes.addFlashAttribute("complete", "Registered!");
		//redirect:/inquiry/form��html�t�@�C�����w���Ă���̂ł͂Ȃ��Aurl���w���Ă���
		//�N���C�A���g�Ƀ��X�|���X��Ԃ��A�N���C�A���g���烊�N�G�X�g���󂯂�
		//���N�G�X�g�X�R�[�v�ł̓f�[�^�ƂȂ����Ƃ��ł��Ȃ�
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
