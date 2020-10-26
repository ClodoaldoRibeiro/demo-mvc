package com.mbalem.cursos.boot.web.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mbalem.cursos.boot.domain.Cargo;
import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.service.CargoServiceImp;
import com.mbalem.cursos.boot.service.DepatamentoServiceImp;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/cargos")	
public class CargoController {
	
	@Autowired
	private CargoServiceImp cargoService;
	@Autowired
	private DepatamentoServiceImp depatamentoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult bindingResult, RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.Inserir(cargo);
		attributes.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, 
						 @RequestParam("page") Optional<Integer> page, 
						 @RequestParam("dir") Optional<String> dir) {
		
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");		
		
		PaginacaoUtil<Cargo> pageCargo = cargoService.buscaPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageCargo", pageCargo);
		return "cargo/lista"; 
	}
	
	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult bindingResult,  RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) { 
			return "cargo/cadastro";
		}
		cargoService.Alterar(cargo);
		attributes.addFlashAttribute("success", "Cargo alterado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String moverDadosFormularioExcluir(@PathVariable("id") Long id, ModelMap modelMap) {

		if (cargoService.cargoTemFuncionario(id)) {
			modelMap.addAttribute("fail", "Cargo não removido. Possui Departamento(s) vinculado(s).");
		} else {
			cargoService.Excluir(id);
			modelMap.addAttribute("success", "Cargo excluído com sucesso.");
		}

		return "cargo/cadastro";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listarDepartamento(){
		return depatamentoService.buscarTodos();
	}

}

