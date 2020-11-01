package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface VeiculoService {

	void Inserir(Veiculo veiculo);

	void Alterar(Veiculo veiculo);

	void Excluir(Long id);

	Veiculo buscarPorId(Long id);

	List<Veiculo> buscarTodos();
	
	boolean chassiCadastrado(String chassi);

	PaginacaoUtil<Veiculo> buscaPorPagina(int paginaAtual, String ordem);
}
