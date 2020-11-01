package com.mbalem.cursos.boot.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

@Repository
public class VeiculoDaoImp extends AbstractDao<Veiculo, Long> implements VeiculoDao {

	public boolean findByChassi(String classi) {

		
		Veiculo veiculo;

		EntityManager entityManager = super.getEntityManager();
		Query query = entityManager.createQuery("select * from Veiculo where chassi = :classi");
		query.setParameter("chassi", classi);

		veiculo = (Veiculo) query.getSingleResult();

		if (veiculo != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PaginacaoUtil<Veiculo> buscaPaginada(int pagina, String direcao) {
		// TODO Auto-generated method stub
		return null;
	}

}
