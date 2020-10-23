package com.mbalem.cursos.boot.service;

import java.util.List;

import com.mbalem.cursos.boot.domain.Cargo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;
	
public interface CargoService {

	void Inserir(Cargo cargo);

	void Alterar(Cargo cargo);

	void Excluir(Long id);

	Cargo buscarPorId(Long id);

	List<Cargo> buscarTodos();
	
	boolean cargoTemFuncionario(Long id);
	
	PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcao);
}
