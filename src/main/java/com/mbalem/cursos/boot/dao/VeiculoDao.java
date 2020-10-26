package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface VeiculoDao {

	void save(Veiculo veiculo);

	void update(Veiculo veiculo);

	void delete(Long id);

	Veiculo findById(Long id);

	List<Veiculo> findAll();

	PaginacaoUtil<Veiculo> buscaPaginada(int pagina, String direcao);

}
