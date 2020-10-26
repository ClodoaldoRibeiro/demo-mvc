package com.mbalem.cursos.boot.dao;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class CombustivelImp extends AbstractDao<Combustivel, Long> implements CombustivelDao {

	@Override
	public PaginacaoUtil<Combustivel> buscaPaginada(int pagina, String direcao) {
		// TODO Auto-generated method stub
		return null;
	}

}
