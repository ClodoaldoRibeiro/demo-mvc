package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class MarcaDaoImp extends AbstractDao<Marca, Long> implements MarcaDao {

	public PaginacaoUtil<Marca> buscaPaginada(int pagina, String direcao) {
		int tamanho = 7;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

		List<Marca> Marcas = getEntityManager()
				.createQuery("select m from Marca m order by m.nome " + direcao, Marca.class).setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<Marca>(tamanho, pagina, totalDePaginas, direcao, Marcas);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from Marca", Long.class).getSingleResult();
	}

}
