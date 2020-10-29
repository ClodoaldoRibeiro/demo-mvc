package com.mbalem.cursos.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.domain.UF;
import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.service.CombustivelService;
import com.mbalem.cursos.boot.service.MarcaService;
import com.mbalem.cursos.boot.service.TiposVeiculosService;
import com.mbalem.cursos.boot.service.VeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private MarcaService MarcaService;

	@Autowired
	private TiposVeiculosService tiposVeiculosService;

	@Autowired
	private CombustivelService combustivelService;

	@GetMapping("/cadastrar")
	public String cadastrar(Veiculo veiculo) {
		return "veiculo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("veiculos", veiculoService.buscarTodos());
		return "veiculo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Veiculo veiculo, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "veiculo/cadastro";
		}

		veiculoService.Inserir(veiculo);
		attributes.addFlashAttribute("success", "Veículo inserido com sucesso.");
		return "redirect:/veiculos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("veiculo", veiculoService.buscarPorId(id));
		return "veiculo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Veiculo veiculo, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "veiculo/cadastro";
		}

		veiculoService.Alterar(veiculo);
		attributes.addFlashAttribute("success", "Veículo alterado com sucesso.");
		return "redirect:/veiculos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluirPorId(@PathVariable("id") Long id, ModelMap modelMap) {
		veiculoService.Excluir(id);
		modelMap.addAttribute("success", "Veículo excluído com sucesso.");
		return "redirect:/veiculos/listar";
	}

	@ModelAttribute("marcas")
	public List<Marca> getMarcas() {
		return MarcaService.buscarTodos();
	}

	@ModelAttribute("tiposVeiculos")
	public List<TiposVeiculos> getTiposVeiculos() {
		return tiposVeiculosService.buscarTodos();
	}

	@ModelAttribute("combustiveis")
	public List<Combustivel> getCombustiveis() {
		return combustivelService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}

}
