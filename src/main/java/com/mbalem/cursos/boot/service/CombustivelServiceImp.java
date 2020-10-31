package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.CombustivelDao;
import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class CombustivelServiceImp implements CombustivelService {

	@Autowired
	private CombustivelDao combustivelDao;

	@Override
	public void Inserir(Combustivel combustivel) {
		combustivelDao.save(combustivel);
	}

	@Override
	public void Alterar(Combustivel combustivel) {
		combustivelDao.update(combustivel);
	}

	@Override
	public void Excluir(Long id) {
		combustivelDao.delete(id);
	}

	@Override
	public Combustivel buscarPorId(Long id) {
		return combustivelDao.findById(id);
	}

	@Override
	public List<Combustivel> buscarTodos() {
		return combustivelDao.findAll();
	}

	@Override
	public PaginacaoUtil<Combustivel> buscaPorPagina(int paginaAtual, String ordem) {
		return combustivelDao.buscaPaginada(paginaAtual, ordem);
	}

	@Override
	public boolean combustivelTemVeiculos(Long id) {

		if (buscarPorId(id).getVeiculo().isEmpty()) {
			return false;
		}

		return true;
	}

}
