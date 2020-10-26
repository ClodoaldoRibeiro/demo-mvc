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

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.service.TiposVeiculosService;

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
	public String listar(ModelMap model) {
		model.addAttribute("tiposVeiculos", tiposVeiculosService.buscarTodos());
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
	public String excluir(@PathVariable("id") Long id, ModelMap modelMap) {

		if (tiposVeiculosService.tiposVeiculosTemVeiculos(id)) {
			modelMap.addAttribute("fail", "Tipo de Veículo não removido. Possui Veículo(s) vinculado(s).");
		} else {
			tiposVeiculosService.Excluir(id);
			modelMap.addAttribute("success", "Tipo de Veículo excluído com sucesso.");
		}

		return listar(modelMap);
	}

}
