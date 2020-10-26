package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface TiposVeiculosDao {

	void save(TiposVeiculos tiposVeiculos);

	void update(TiposVeiculos tiposVeiculos);

	void delete(Long id);

	TiposVeiculos findById(Long id);

	List<TiposVeiculos> findAll();

	PaginacaoUtil<TiposVeiculos> buscaPaginada(int pagina, String direcao);

}
