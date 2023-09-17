package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.InquiryNotFoundException;


/**
 * �S�Ă�Controller�ŋ��ʏ������`
 */
@ControllerAdvice//���ʏ���������
public class WebMvcControllerAdvice {

	/*
	 * This method changes empty character to null
	 * ������̃��\�b�h��p�ӂ��Ă����Ƒ��M���ꂽ�󕶎���null�ɕϊ�����܂�
	 */
    @InitBinder//�󕶎���null�ɂ���
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleException(EmptyResultDataAccessException e, Model model) {
    	model.addAttribute("message", e);
    	return "error/CustomPage";
    }
    
    @ExceptionHandler(InquiryNotFoundException.class)
	public String handleException(InquiryNotFoundException e, Model model) {
		model.addAttribute("message", e);
		return "error/CusomPage";
	}
   
}