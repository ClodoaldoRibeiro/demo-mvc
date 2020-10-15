package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.CargoDao;
import com.mbalem.cursos.boot.domain.Cargo;

@Service
@Transactional(readOnly = false)
public class CargoServiceImp implements CargoService {

	@Autowired
	private CargoDao cargoDao;

	@Override
	public void Inserir(Cargo cargo) {
		cargoDao.save(cargo);
	}

	@Override
	public void Alterar(Cargo cargo) {
		cargoDao.update(cargo);
	}

	@Override
	public void Excluir(Long id) {
		cargoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		return cargoDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		return cargoDao.findAll();
	}

	@Override
	public boolean cargoTemFuncionario(Long id) {
		
		if (buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		
		return true;
	}

}
