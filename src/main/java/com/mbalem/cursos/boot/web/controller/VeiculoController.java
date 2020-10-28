package com.mbalem.cursos.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
