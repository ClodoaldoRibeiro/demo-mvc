package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface CombustivelService {

	void Inserir(Combustivel combustivel);

	void Alterar(Combustivel combustivel);

	void Excluir(Long id);

	Combustivel buscarPorId(Long id);

	List<Combustivel> buscarTodos();

	PaginacaoUtil<Combustivel> buscaPorPagina(int paginaAtual, String ordem);

}
