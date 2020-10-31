package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class TiposVeiculosDaoImp extends AbstractDao<TiposVeiculos, Long> implements TiposVeiculosDao {

	public PaginacaoUtil<TiposVeiculos> buscaPaginada(int pagina, String direcao) {
		int tamanho = 7;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

		List<TiposVeiculos> TiposVeiculoss = getEntityManager()
				.createQuery("select t from TiposVeiculos t order by t.nome " + direcao, TiposVeiculos.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<TiposVeiculos>(tamanho, pagina, totalDePaginas, direcao, TiposVeiculoss);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from TiposVeiculos", Long.class).getSingleResult();
	}

}
