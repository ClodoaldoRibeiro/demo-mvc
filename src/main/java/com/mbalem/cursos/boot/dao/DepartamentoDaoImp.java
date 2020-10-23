package com.mbalem.cursos.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class DepartamentoDaoImp extends AbstractDao<Departamento, Long> implements DepartamentoDao {
	
	public PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao) {
		int tamanho = 7;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

		List<Departamento> derpartamentos = getEntityManager()
				.createQuery("select d from Departamento d order by d.nome " + direcao, Departamento.class).setFirstResult(inicio)
				.setMaxResults(tamanho).getResultList();

		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<Departamento>(tamanho, pagina, totalDePaginas, direcao, derpartamentos);
	}

	public long count() {
		return getEntityManager().createQuery("select count(*) from Departamento", Long.class).getSingleResult();
	}

}
