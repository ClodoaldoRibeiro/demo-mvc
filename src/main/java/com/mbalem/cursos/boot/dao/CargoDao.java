package com.mbalem.cursos.boot.dao;

import java.util.List;

import com.mbalem.cursos.boot.domain.Cargo;
import com.mbalem.cursos.boot.util.PaginacaoUtil;

public interface CargoDao {

    void save(Cargo cargo );

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
    
    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}

