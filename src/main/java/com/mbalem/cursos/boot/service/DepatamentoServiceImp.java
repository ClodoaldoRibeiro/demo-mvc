package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.DepartamentoDao;
import com.mbalem.cursos.boot.domain.Departamento;

@Service
@Transactional(readOnly = false)
public class DepatamentoServiceImp implements DepatamentoService {

	@Autowired
	private DepartamentoDao departamentoDao;

	@Override
	public void Inserir(Departamento departamento) {
		departamentoDao.save(departamento);
	}

	@Override
	public void Alterar(Departamento departamento) {
		departamentoDao.update(departamento);
	}

	@Override
	public void Excluir(Long id) {
		departamentoDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {
		return departamentoDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {
		return departamentoDao.findAll();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		
		if (buscarPorId(id).getCargos().isEmpty()) {
			return false;
		}
		
		return true;
	}
	


}
