package com.mbalem.cursos.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbalem.cursos.boot.dao.MarcaDao;
import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class MarcaServiceImp implements MarcaService {

	@Autowired
	private MarcaDao marcaDao;

	@Override
	public void Inserir(Marca marca) {
		marcaDao.save(marca);
	}

	@Override
	public void Alterar(Marca marca) {
		marcaDao.update(marca);
	}

	@Override
	public void Excluir(Long id) {
		marcaDao.delete(id);
	}

	@Override
	public Marca buscarPorId(Long id) {
		return marcaDao.findById(id);
	}

	@Override
	public List<Marca> buscarTodos() {
		return marcaDao.findAll();
	}

	@Override
	public boolean marcaTemVeiculos(Long id) {
		if (buscarPorId(id).getVeiculo().isEmpty()) {
			return false;
		}

		return true;

	}

	@Override
	public PaginacaoUtil<Marca> buscaPorPagina(int paginaAtual, String ordem) {
		// TODO Auto-generated method stub
		return null;
	}

}
