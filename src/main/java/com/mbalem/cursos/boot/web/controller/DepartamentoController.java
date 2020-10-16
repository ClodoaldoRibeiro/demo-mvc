package com.mbalem.cursos.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.service.DepatamentoServiceImp;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepatamentoServiceImp service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("departamentos", service.buscarTodos());
		return "/departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "/departamento/cadastro";
		}

		service.Inserir(departamento);
		attributes.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "/departamento/cadastro";
		}
		service.Alterar(departamento);
		attributes.addFlashAttribute("success", "Departamento alteraçao com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String moverDadosFormularioExcluir(@PathVariable("id") Long id, ModelMap modelMap) {

		if (service.departamentoTemCargos(id)) {
			modelMap.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.Excluir(id);
			modelMap.addAttribute("success", "Departamento excluído com sucesso.");
		}

		return listar(modelMap);
	}

}
