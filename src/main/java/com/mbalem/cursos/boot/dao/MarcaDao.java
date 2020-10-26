package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface MarcaDao {

	void save(Marca marca);

	void update(Marca marca);

	void delete(Long id);

	Marca findById(Long id);

	List<Marca> findAll();

	PaginacaoUtil<Marca> buscaPaginada(int pagina, String direcao);

}
