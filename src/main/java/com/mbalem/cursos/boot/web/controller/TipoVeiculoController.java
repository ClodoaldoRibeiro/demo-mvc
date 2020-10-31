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

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.service.TiposVeiculosService;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/tiposVeiculos")
public class TipoVeiculoController {

	@Autowired
	private TiposVeiculosService tiposVeiculosService;

	@GetMapping("/cadastrar")
	public String cadastrar(TiposVeiculos tiposVeiculos) {
		return "tipoVeiculo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("dir") Optional<String> dir) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");

		PaginacaoUtil<TiposVeiculos> pageTiposVeiculos = tiposVeiculosService.buscaPorPagina(paginaAtual, ordem);

		model.addAttribute("pageTiposVeiculos", pageTiposVeiculos);

		return "tipoVeiculo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid TiposVeiculos tiposVeiculos, BindingResult bindingResult,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "tipoVeiculo/cadastro";
		}

		tiposVeiculosService.Inserir(tiposVeiculos);
		attributes.addFlashAttribute("success", "Tipo de Veículo inserido com sucesso.");
		return "redirect:/tiposVeiculos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("tiposVeiculos", tiposVeiculosService.buscarPorId(id));
		return "tipoVeiculo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid TiposVeiculos tiposVeiculos, BindingResult bindingResult,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "tipoVeiculo/cadastro";
		}
		tiposVeiculosService.Alterar(tiposVeiculos);
		attributes.addFlashAttribute("success", "Tipo de Veículo alterado com sucesso.");
		return "redirect:/tiposVeiculos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

		if (tiposVeiculosService.tiposVeiculosTemVeiculos(id)) {
			attr.addFlashAttribute("fail", "Tipo de Veículo não removido. Possui Veículo(s) vinculado(s).");
		} else {
			tiposVeiculosService.Excluir(id);
			attr.addFlashAttribute("success", "Tipo de Veículo excluído com sucesso.");
		}

		return "redirect:/tiposVeiculos/listar";
	}

}
