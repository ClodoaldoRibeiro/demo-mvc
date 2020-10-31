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

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.service.MarcaService;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Marca marca) {
		return "marca/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("dir") Optional<String> dir) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");

		PaginacaoUtil<Marca> pageMarca = marcaService.buscaPorPagina(paginaAtual, ordem);

		model.addAttribute("pageMarca", pageMarca);

		return "marca/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Marca marca, BindingResult bindingResult, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "marca/cadastro";
		}

		marcaService.Inserir(marca);
		attributes.addFlashAttribute("success", "Marca inserida com sucesso.");
		return "redirect:/marcas/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String moverDadosFormularioEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("marca", marcaService.buscarPorId(id));
		return "marca/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Marca marca, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "marca/cadastro";
		}
		marcaService.Alterar(marca);
		attributes.addFlashAttribute("success", "Marca alterada com sucesso.");
		return "redirect:/marcas/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {

		if (marcaService.marcaTemVeiculos(id)) {
			attributes.addFlashAttribute("fail", "Marca não removida. Possui Veículo(s) vinculado(s).");
		} else {
			marcaService.Excluir(id);
			attributes.addFlashAttribute("success", "Marca excluída com sucesso.");
		}

		return "redirect:/marcas/listar";
	}

}
