package com.firstproject.cosmetic.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.firstproject.cosmetic.entity.Makeup;
import com.firstproject.cosmetic.service.MakeupService;
@Controller

public class MakeupController {
	@Autowired
	MakeupService makeupService;

	@RequestMapping("/list")
	public String getAllMakeup(Model bm)
	{
		List<Makeup> allmakeup= makeupService.getAllMakeup();
		bm.addAttribute("makeups",allmakeup);
		return "/list-makeup";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Makeup m1=new Makeup();
		theModel.addAttribute("makeup",m1);
		return "/makeup-form";	
	}

	@PostMapping("/save")
	public String addMakeupByObjectBody(
	@ModelAttribute("makeup") Makeup m1)
	{
	makeupService.addMakeup(m1);
	return "redirect:/list";
	}
	
	@PostMapping("/delete")
	String deleteMakeupById(@RequestParam("makeupId") int midd)
	{
		makeupService.deleteById(midd);
		return "redirect:/list";
	}
	
	@PostMapping("/showFormForUpdate")
	String updateBook(@RequestParam("makeupId") int midd,
					Model theModel)
	{
		Makeup dbmakeup=makeupService.getMakeupsById(midd);
		theModel.addAttribute("makeup",dbmakeup);
		return "/makeup-form";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
