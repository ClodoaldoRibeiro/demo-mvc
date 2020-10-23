package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Cargo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class CargoDaoImp extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
		int tamanho = 7;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

		List<Cargo> cargos = getEntityManager()
				.createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class).setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<Cargo>(tamanho, pagina, totalDePaginas, direcao, cargos);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
	}
}
