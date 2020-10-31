package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class CombustivelDaoImp extends AbstractDao<Combustivel, Long> implements CombustivelDao {

	public PaginacaoUtil<Combustivel> buscaPaginada(int pagina, String direcao) {
		int tamanho = 7;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

		List<Combustivel> derpartamentos = getEntityManager()
				.createQuery("select c from Combustivel c order by c.nome " + direcao, Combustivel.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<Combustivel>(tamanho, pagina, totalDePaginas, direcao, derpartamentos);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from Combustivel", Long.class).getSingleResult();
	}

}
