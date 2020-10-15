package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.Departamento;

public interface DepartamentoDao {

	public void save(Departamento departamento);

	public void update(Departamento departamento);

	public void delete(Long id);

	public Departamento findById(Long id);

	public List<Departamento> findAll();
}
