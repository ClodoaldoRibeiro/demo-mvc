package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.TiposVeiculosDao;
import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class TiposVeiculosServiceImp implements TiposVeiculosService {

	@Autowired
	private TiposVeiculosDao tiposVeiculosDao;

	@Override
	public void Inserir(TiposVeiculos tiposVeiculos) {
		tiposVeiculosDao.save(tiposVeiculos);
	}

	@Override
	public void Alterar(TiposVeiculos tiposVeiculos) {
		tiposVeiculosDao.update(tiposVeiculos);
	}

	@Override
	public void Excluir(Long id) {
		tiposVeiculosDao.delete(id);
	}

	@Override
	public TiposVeiculos buscarPorId(Long id) {
		return tiposVeiculosDao.findById(id);
	}

	@Override
	public List<TiposVeiculos> buscarTodos() {
		return tiposVeiculosDao.findAll();
	}

	@Override
	public PaginacaoUtil<TiposVeiculos> buscaPorPagina(int pagina, String direcao) {
		return tiposVeiculosDao.buscaPaginada(pagina, direcao);
	}

	@Override
	public boolean tiposVeiculosTemVeiculos(Long id) {

		if (buscarPorId(id).getVeiculo().isEmpty()) {
			return false;
		}

		return true;
	}

}
