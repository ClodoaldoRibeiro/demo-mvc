package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface TiposVeiculosService {

	void Inserir(TiposVeiculos tiposVeiculos);

	void Alterar(TiposVeiculos tiposVeiculos);

	void Excluir(Long id);

	TiposVeiculos buscarPorId(Long id);

	List<TiposVeiculos> buscarTodos();

	PaginacaoUtil<TiposVeiculos> buscaPorPagina(int pagina, String direcao);

	boolean tiposVeiculosTemVeiculos(Long id);

}
