package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImp extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByNome(String nome) {
		return createQuery("select f from Funcionario f where f.nome like concat('%',?1,'%') ", nome);
	}

	@Override
	public List<Funcionario> findByCargoId(Long id) {
		return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
	}

	
}
