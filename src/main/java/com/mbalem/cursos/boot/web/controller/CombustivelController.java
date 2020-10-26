package com.mbalem.cursos.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/combustiveis")
public class CombustivelController {
	

	@GetMapping("/listar")
	public String listare(ModelMap modelMap) {
		return "combustivel/lista";
	}

}
