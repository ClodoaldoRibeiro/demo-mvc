package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface DepatamentoService {
	
	void Inserir(Departamento departamento);

	void Alterar(Departamento departamento);

	void Excluir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();
	
	boolean departamentoTemCargos(Long id);

	PaginacaoUtil<Departamento> buscaPorPagina(int paginaAtual, String ordem);

}	
