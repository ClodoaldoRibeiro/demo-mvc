package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface MarcaService {

	void Inserir(Marca marca);

	void Alterar(Marca marca);

	void Excluir(Long id);

	Marca buscarPorId(Long id);

	List<Marca> buscarTodos();

	PaginacaoUtil<Marca> buscaPorPagina(int paginaAtual, String ordem);

}
