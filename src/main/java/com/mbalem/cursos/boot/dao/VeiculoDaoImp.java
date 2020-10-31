package com.mbalem.cursos.boot.dao;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class VeiculoDaoImp extends AbstractDao<Veiculo, Long> implements VeiculoDao {

	@Override
	public PaginacaoUtil<Veiculo> buscaPaginada(int pagina, String direcao) {
		// TODO Auto-generated method stub
		return null;
	}

}
