package com.mbalem.cursos.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbalem.cursos.boot.domain.Cargo;
import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.service.CargoServiceImp;
import com.mbalem.cursos.boot.service.DepatamentoServiceImp;

@Controller
@RequestMapping("/cargos")	
public class CargoController {
	
	@Autowired
	private CargoServiceImp service;
	@Autowired
	private DepatamentoServiceImp depatamentoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attributes) {
		service.Inserir(cargo);
		attributes.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("cargos", service.buscarTodos());
		return "/cargo/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("cargo", service.buscarPorId(id));
		return "/cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Cargo cargo, RedirectAttributes attributes) {
		service.Alterar(cargo);
		attributes.addFlashAttribute("success", "Cargo alterado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String moverDadosFormularioExcluir(@PathVariable("id") Long id, ModelMap modelMap) {

		if (service.cargoTemFuncionario(id)) {
			modelMap.addAttribute("fail", "Cargo não removido. Possui cargo(s) vinculado(s).");
		} else {
			service.Excluir(id);
			modelMap.addAttribute("success", "Cargo excluído com sucesso.");
		}

		return listar(modelMap);
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listarDepartamento(){
		return depatamentoService.buscarTodos();
	}

}

