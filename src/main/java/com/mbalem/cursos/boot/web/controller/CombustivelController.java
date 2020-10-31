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

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.service.CombustivelService;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/combustiveis")
public class CombustivelController {

	@Autowired
	private CombustivelService combustivelService;

	@GetMapping("/cadastrar")
	public String cadastrar(Combustivel combustivel) {
		return "combustivel/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("dir") Optional<String> dir) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");

		System.out.println("paginaAtual" + paginaAtual);
		System.out.println("ordem" + ordem);

		PaginacaoUtil<Combustivel> pageCombustivel = combustivelService.buscaPorPagina(paginaAtual, ordem);

		model.addAttribute("pageCombustivel", pageCombustivel);
		return "combustivel/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Combustivel combustivel, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "combustivel/cadastro";
		}

		combustivelService.Inserir(combustivel);
		attributes.addFlashAttribute("success", "Combustivel inserido com sucesso.");
		return "redirect:/combustiveis/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("combustivel", combustivelService.buscarPorId(id));
		return "combustivel/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Combustivel combustivel, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "combustivel/cadastro";
		}
		combustivelService.Alterar(combustivel);
		attributes.addFlashAttribute("success", "Combustível alterado com sucesso.");
		return "redirect:/combustiveis/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String moverDadosFormularioExcluir(@PathVariable("id") Long id, RedirectAttributes attributes) {

		if (combustivelService.combustivelTemVeiculos(id)) {
			attributes.addFlashAttribute("fail", "Combustível não removido. Possui Veículo(s) vinculado(s).");
		} else {
			combustivelService.Excluir(id);
			attributes.addFlashAttribute("success", "Combustível excluído com sucesso.");
		}

		return "redirect:/combustiveis/listar";
	}

}
