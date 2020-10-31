package com.mbalem.cursos.boot.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.service.DepatamentoService;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepatamentoService derpartamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("dir") Optional<String> dir) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");

		PaginacaoUtil<Departamento> pageDerpartamento = derpartamentoService.buscaPorPagina(paginaAtual, ordem);

		model.addAttribute("pageDepartamento", pageDerpartamento);

		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "departamento/cadastro";
		}

		derpartamentoService.Inserir(departamento);
		attributes.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("departamento", derpartamentoService.buscarPorId(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "departamento/cadastro";
		}
		derpartamentoService.Alterar(departamento);
		attributes.addFlashAttribute("success", "Departamento alterado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String moverDadosFormularioExcluir(@PathVariable("id") Long id, RedirectAttributes attr) {

		if (derpartamentoService.departamentoTemCargos(id)) {
			attr.addFlashAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			derpartamentoService.Excluir(id);
			attr.addFlashAttribute("success", "Departamento excluído com sucesso.");
		}

		return "redirect:/departamentos/listar";
	}

}
