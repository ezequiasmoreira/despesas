package com.ezequias.despesas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezequias.despesas.domain.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer>{
	
}

