package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.VeiculoDao;
import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class VeiculoServiceImp implements VeiculoService {

	@Autowired
	private VeiculoDao veiculoDao;

	@Override
	public void Inserir(Veiculo veiculo) {
		veiculoDao.save(veiculo);
	}

	@Override
	public void Alterar(Veiculo veiculo) {
		veiculoDao.update(veiculo);
	}

	@Override
	public void Excluir(Long id) {
		veiculoDao.delete(id);
	}

	@Override
	public Veiculo buscarPorId(Long id) {
		return veiculoDao.findById(id);
	}

	@Override
	public List<Veiculo> buscarTodos() {
		return veiculoDao.findAll();
	}

	@Override
	public PaginacaoUtil<Veiculo> buscaPorPagina(int paginaAtual, String ordem) {
		return veiculoDao.buscaPaginada(paginaAtual, ordem);
	}

}
