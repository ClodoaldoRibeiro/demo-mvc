package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface CombustivelDao {

	void save(Combustivel combustivel);

	void update(Combustivel combustivel);

	void delete(Long id);

	Combustivel findById(Long id);

	List<Combustivel> findAll();

	PaginacaoUtil<Combustivel> buscaPaginada(int pagina, String direcao);
}
