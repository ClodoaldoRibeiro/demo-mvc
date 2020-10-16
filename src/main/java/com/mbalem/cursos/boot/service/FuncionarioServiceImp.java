package com.mbalem.cursos.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.FuncionarioDao;
import com.mbalem.cursos.boot.domain.Funcionario;

@Service
@Transactional(readOnly = false)
public class FuncionarioServiceImp implements FuncionarioService {

	@Autowired
	private FuncionarioDao funcionarioDao;

	@Override
	public void Inserir(Funcionario funcionario) {
		funcionarioDao.save(funcionario);
	}

	@Override
	public void Alterar(Funcionario funcionario) {
		funcionarioDao.update(funcionario);
	}

	@Override
	public void Excluir(Long id) {
		funcionarioDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return funcionarioDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return funcionarioDao.findAll();
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		return funcionarioDao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscarPorCargoId(Long id) {
		return funcionarioDao.findByCargoId(id);
	}

	@Override
	public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {

		if (entrada != null && saida != null) {	    	
            return funcionarioDao.findByDataEntradaDataSaida(entrada, saida);
        } else if (entrada != null) {        	
	        return funcionarioDao.findByDataEntrada(entrada);
        } else if (saida != null) {        	
	        return funcionarioDao.findByDataSaida(saida);
        } else {
        	return new ArrayList<>();
        }
	}

}
