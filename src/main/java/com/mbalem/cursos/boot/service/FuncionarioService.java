package com.mbalem.cursos.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.mbalem.cursos.boot.domain.Funcionario;

public interface FuncionarioService {
	
	void Inserir(Funcionario funcionario);

	void Alterar(Funcionario funcionario);

	void Excluir(Long id);

	Funcionario buscarPorId(Long id);

	List<Funcionario> buscarTodos();
	
	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> buscarPorCargoId(Long id);

	List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);

}
