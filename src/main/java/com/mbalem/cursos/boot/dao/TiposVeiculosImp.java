package com.mbalem.cursos.boot.dao;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class TiposVeiculosImp extends AbstractDao<TiposVeiculos, Long> implements TiposVeiculosDao {

	@Override
	public PaginacaoUtil<TiposVeiculos> buscaPaginada(int pagina, String direcao) {
		// TODO Auto-generated method stub
		return null;
	}

}
