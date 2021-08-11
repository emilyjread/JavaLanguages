package com.example.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.languages.models.Language;
import com.example.languages.services.LanguageService;


@Controller
public class LanguageController {
	
	private final LanguageService languageService;
	
	 public LanguageController(LanguageService languageService) {
	        this.languageService = languageService;
	    }
	 
	//SHOW ALL LANGUAGES
	@RequestMapping("/languages")
   public String index(Model model) {
       List<Language> languages = languageService.allLanguages();
       model.addAttribute("languages", languages);
       return "/languages/index.jsp";
   }
	
	//SHOW ONE LANGUAGE
	@RequestMapping("/languages/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
	    model.addAttribute("language", language);
	    return "/languages/show.jsp";
	}
	
	//CREATE LANGUAGE
	@RequestMapping("/languages/new")
	public String newLanguage(@ModelAttribute("language") Language language) {
		return "/languages/new.jsp";
		
	}
	
	@RequestMapping(value= "/languages", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("language") Language language, BindingResult result){
		if(result.hasErrors()) {
			return "/languages/new.jsp";
		}
		else {
			languageService.createLanguage(language);
			return "redirect:/languages";
		}
		
	}
	
	//UPDATE LANGUAGE
	@RequestMapping("/languages/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
	    model.addAttribute("language", language);
	    return "/languages/edit.jsp";
	}
	
	@RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("language") Language language, BindingResult result, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/languages/edit.jsp";
       } else {
    	   languageService.updateLanguage(language.getId(), language.getName(), language.getCreator(), language.getCurrentVersion());
           return "redirect:/languages";
       }
	}
	
	//DELETE LANGUAGE
	@RequestMapping(value="/languages/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable ("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
		
	}

}
