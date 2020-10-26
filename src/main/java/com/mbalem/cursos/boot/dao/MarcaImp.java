package com.mbalem.cursos.boot.dao;


import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class MarcaImp extends AbstractDao<Marca, Long>  implements MarcaDao {

	@Override
	public PaginacaoUtil<Marca> buscaPaginada(int pagina, String direcao) {
		// TODO Auto-generated method stub
		return null;
	}

}
