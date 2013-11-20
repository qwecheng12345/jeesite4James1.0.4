/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.account.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.account.entity.Subject;
import com.thinkgem.jeesite.modules.account.service.SubjectService;

/**
 * Subject Controller
 * @author James
 * @version 2013-11-18
 */
@Controller
@RequestMapping(value = "${adminPath}/account/subject")
public class SubjectController extends BaseController {
	
	@Autowired
	private SubjectService subjectService;
	
	@ModelAttribute
	public Subject get(@RequestParam(required=false) Long id) {
		if (id != null){
			return subjectService.getSubject(id);
		}else{
			return new Subject();
		}
	}
	
	@RequestMapping(value = "")
	public String incoming(Model model, String type) {
		type = type == null ? "0" : type;
		List<Subject> list = Lists.newArrayList();
		List<Subject> sourcelist = subjectService.findAllSubjects(type);
		Subject.sortList(list, sourcelist, 1L);
		model.addAttribute("subjectList", list);
		model.addAttribute("type", type);
		return "modules/account/subjectList";
	}
	
	@RequestMapping(value = "operation")
	public String operation(Model model, String oper, String type) {
		if (oper.equals("1")) {
			
		} else if (oper.equals("2")) {
			List<Subject> list = subjectService.findAllSubjects(type, Subject.LEVEL1);
			model.addAttribute("list", list);
		}
		model.addAttribute("type", type);
		return "modules/account/subjectForm";
	}
}
